<?php
$defaultLang = "it.ini";
$langIni = $defaultLang;
$pwmon = "";
$weekcode = "";
$error = "";
$memberID = "";
$weeks = array ();
$ministry = array ();
$christians = array ();
$activities = array ();

if (isset ( $_GET ["lang"] )) {
	if (! empty ( $_GET ["lang"] )) {
		$langIni = $_GET ["lang"] . ".ini";
	}
}

if (! file_exists ( "languages/" . $langIni )) {
	$langIni = $defaultLang;
}

if (file_exists ( "languages/" . $langIni )) {
	
	$language = parse_ini_file ( "languages/" . $langIni );
	
	// Password monitor
	if (isset ( $_GET ["pwmon"] )) {
		if (! empty ( $_GET ["pwmon"] )) {
			$pwmon = $_GET ["pwmon"];
			
			// Weekcode
			$weekcode = date ( "oW" );
			
			// Database connection
			require_once dirname ( __DIR__, 1 ) . '/config.php';
			$database = mysqli_connect ( DB_SERVER, DB_USER, DB_PASSWORD, DB_DATABASE );
			mysqli_set_charset ( $conn, 'utf8' );
			if (! $database) {
				$error = "Database connection error: " . mysqli_connect_error ();
			} else {
				
				$query_mem = "SELECT spMemberID";
				$query_mem .= " FROM sp_members";
				$query_mem .= " WHERE spInf47 =";
				$query_mem .= " '" . $pwmon . "'";
				
				$result_mem = mysqli_query ( $database, $query_mem );
				
				if (mysqli_num_rows ( $result_mem ) > 0) {
					
					$resultRow_mem = $result_mem->fetch_assoc ();
					$resultRow_mem = array_map ( "utf8_encode", $resultRow_mem );
					
					$memberID = $resultRow_mem ["spMemberID"];
					
					// Weeks
					
					$query_week = "SELECT spInf1, spInf3, spInf4, spInf11, spInf14, spInf18, spInf23, spInf27, spInf28";
					$query_week .= " FROM sp_week";
					$query_week .= " WHERE spInf1 >=";
					$query_week .= " " . $weekcode;
					$query_week .= " ORDER BY spInf1 ASC";
					
					$result_week = mysqli_query ( $database, $query_week );
					
					if (mysqli_num_rows ( $result_week ) > 0) {
						
						while ( $resultRow_week = $result_week->fetch_assoc () ) {
							
							$resultRow_week = array_map ( "utf8_encode", $resultRow_week );
							
							$row = array ();
							$row ["spInf1"] = $resultRow_week ["spInf1"];
							$row ["spInf3"] = $resultRow_week ["spInf3"];
							$row ["spInf4"] = $resultRow_week ["spInf4"];
							$row ["spInf11"] = $resultRow_week ["spInf11"];
							$row ["spInf14"] = $resultRow_week ["spInf14"];
							$row ["spInf18"] = $resultRow_week ["spInf18"];
							$row ["spInf23"] = $resultRow_week ["spInf23"];
							$row ["spInf27"] = $resultRow_week ["spInf27"];
							$row ["spInf28"] = $resultRow_week ["spInf28"];
							
							array_push ( $weeks, $row );
						}
					}
					
					$query_ministry = "SELECT spInf1, spInf7, spInf8, spInf9, spInf10";
					$query_ministry .= " FROM sp_week_min";
					$query_ministry .= " WHERE spInf1 >=";
					$query_ministry .= " " . $weekcode;
					$query_ministry .= " ORDER BY spInf1 ASC";
					
					$result_ministry = mysqli_query ( $database, $query_ministry );
					
					if (mysqli_num_rows ( $result_ministry ) > 0) {
						
						while ( $resultRow_ministry = $result_ministry->fetch_assoc () ) {
							
							$resultRow_ministry = array_map ( "utf8_encode", $resultRow_ministry );
							
							$row = array ();
							$row ["spInf1"] = $resultRow_ministry ["spInf1"];
							$row ["spInf7"] = $resultRow_ministry ["spInf7"];
							$row ["spInf8"] = $resultRow_ministry ["spInf8"];
							$row ["spInf9"] = $resultRow_ministry ["spInf9"];
							$row ["spInf10"] = $resultRow_ministry ["spInf10"];
							
							array_push ( $ministry, $row );
						}
					}
					
					$query_christians = "SELECT spInf1, spInf6";
					$query_christians .= " FROM sp_week_cr";
					$query_christians .= " WHERE spInf1 >=";
					$query_christians .= " " . $weekcode;
					$query_christians .= " ORDER BY spInf1 ASC";
					
					$result_christians = mysqli_query ( $database, $query_christians );
					
					if (mysqli_num_rows ( $result_christians ) > 0) {
						
						while ( $resultRow_christians = $result_christians->fetch_assoc () ) {
							
							$resultRow_christians = array_map ( "utf8_encode", $resultRow_christians );
							
							$row = array ();
							$row ["spInf1"] = $resultRow_christians ["spInf1"];
							$row ["spInf6"] = $resultRow_christians ["spInf6"];
							
							array_push ( $christians, $row );
						}
					}
				}
				
				$result_mem->close ();
				$result_week->close ();
				$result_ministry->close ();
				$result_christians->close ();
				mysqli_close ( $database );
				
				foreach ( $weeks as $week ) {
					
					$row_weekcode = $week ['spInf1'];
					
					$year = substr ( $row_weekcode, 0, 4 );
					$weeknr = substr ( $row_weekcode, 4, 2 );
					$date = date ( "Y-m-d", strtotime ( $year . "W" . $weeknr . "1" ) );
					
					if ($memberID == $week ["spInf3"]) {
						add_activity ( $date, $language ['PRESIDENT_MIDWEEK'], $language ['PRESIDENT_MIDWEEK_ICON'] );
					}
					
					if ($memberID == $week ["spInf4"]) {
						add_activity ( $date, $language ['PRAY1_MIDWEEK'], $language ['PRAY1_MIDWEEK_ICON'] );
					}
					
					if ($memberID == $week ["spInf11"]) {
						add_activity ( $date, $language ['TALK_MIDWEEK'], $language ['TALK_MIDWEEK_ICON'] );
					}
					
					if ($memberID == $week ["spInf14"]) {
						add_activity ( $date, $language ['DIGGING_MIDWEEK'], $language ['DIGGING_MIDWEEK_ICON'] );
					}
					
					if ($memberID == $week ["spInf18"]) {
						add_activity ( $date, $language ['BIBLE_READING_A'], $language ['BIBLE_READING_A_ICON'] );
					}
					
					if ($memberID == $week ["spInf23"]) {
						add_activity ( $date, $language ['CONGRBIBLESTUDY_MIDWEEK'], $language ['CONGRBIBLESTUDY_MIDWEEK_ICON'] );
					}
					
					if ($memberID == $week ["spInf27"]) {
						add_activity ( $date, $language ['PRAY2_MIDWEEK'], $language ['PRAY2_MIDWEEK_ICON'] );
					}
					
					if ($memberID == $week ["spInf28"]) {
						add_activity ( $date, $language ['BIBLE_READING_B'], $language ['BIBLE_READING_B_ICON'] );
					}
				}
			}
		}
	}
}
function add_activity($date, $activity_name, $activity_icon) {
	$activity_array = array ();
	
	$activity_array ['date'] = $date;
	$activity_array ['name'] = $activity_name;
	$activity_array ['icon'] = $activity_icon;
	
	array_push ( $activities, $activity_array );
}

?>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>SM-Net: SupportPlanner - Monitor</title>
</head>
<body>

	<?php if (isset ( $language )) { ?>

		<?php if ( empty ( $pwmon )) { ?>
			<p><?php echo $language['error1']?></p>
		<?php } else { ?>
		
			<?php if ( empty ( $memberID)) { ?>
				<p><?php echo $language['error1']?></p>
			<?php } else { ?>
		
		
		
			<?php } ?>
		<?php } ?>
		
	<?php } ?>
</body>
</html>