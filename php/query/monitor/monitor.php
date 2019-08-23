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
			}
		}
	}
}

?>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>SM-Net: SupportPlanner 1.0 - Monitor</title>
</head>
<body>

	<?php if (isset ( $language )) { ?>

		<?php if ( empty ( $pwmon )) { ?>
			<p><?php echo $language['error1']?></p>
		<?php } else { ?>
		
		<?php } ?>
		
	<?php } ?>
</body>
</html>
