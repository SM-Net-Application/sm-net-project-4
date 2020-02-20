<?php
$defaultLang = "it.ini";
$langIni = $defaultLang;
$pwmon = "";
$weekcode = "";
$error = "";
$memberID = "";
$weeks = array();
$ministry = array();
$christians = array();
$activities = array();

if (isset($_GET["lang"])) {
    if (! empty($_GET["lang"])) {
        $langIni = $_GET["lang"] . ".ini";
    }
}

if (! file_exists("languages/" . $langIni)) {
    $langIni = $defaultLang;
}

if (file_exists("languages/" . $langIni)) {

    $language = parse_ini_file("languages/" . $langIni);

    $president2icon = $language['MINISTRY_PRESIDENTICON'];

    // Password monitor
    if (isset($_GET["pwmon"])) {
        if (! empty($_GET["pwmon"])) {
            $pwmon = $_GET["pwmon"];

            // Weekcode
            $timestamp = strtotime("Next Sunday");

            $day = date("j", $timestamp);
            $month = date("n", $timestamp);
            $year = date("o", $timestamp);

            if ($month == 1)
                if ($day < 4)
                    $year = $year - 1;

            $week = date("W", $timestamp);

            $weekcode = $year . $week;

            // Database connection
            require_once dirname(__DIR__, 1) . '/config.php';
            $database = mysqli_connect(DB_SERVER, DB_USER, DB_PASSWORD, DB_DATABASE);
            mysqli_set_charset($database, 'utf8');
            if (! $database) {
                $error = "Database connection error: " . mysqli_connect_error();
            } else {

                $query_mem = "SELECT spMemberID";
                $query_mem .= " FROM sp_members";
                $query_mem .= " WHERE spInf47 =";
                $query_mem .= " '" . $pwmon . "'";

                $result_mem = mysqli_query($database, $query_mem);

                if (mysqli_num_rows($result_mem) > 0) {

                    $resultRow_mem = $result_mem->fetch_assoc();
                    $resultRow_mem = array_map("utf8_encode", $resultRow_mem);

                    $memberID = $resultRow_mem["spMemberID"];

                    // Weeks

                    $query_week = "SELECT spInf1, spInf3, spInf4, spInf11, spInf14, spInf18, spInf23, spInf27, spInf28,";
                    $query_week .= " spInf29, spInf30, spInf37, spInf38, spInf40";
                    $query_week .= " FROM sp_week";
                    $query_week .= " WHERE spInf1 >=";
                    $query_week .= " " . $weekcode;
                    $query_week .= " ORDER BY spInf1 ASC";

                    $result_week = mysqli_query($database, $query_week);

                    if (mysqli_num_rows($result_week) > 0) {

                        while ($resultRow_week = $result_week->fetch_assoc()) {

                            $resultRow_week = array_map("utf8_encode", $resultRow_week);

                            $row = array();
                            $row["spInf1"] = $resultRow_week["spInf1"];
                            $row["spInf3"] = $resultRow_week["spInf3"];
                            $row["spInf4"] = $resultRow_week["spInf4"];
                            $row["spInf11"] = $resultRow_week["spInf11"];
                            $row["spInf14"] = $resultRow_week["spInf14"];
                            $row["spInf18"] = $resultRow_week["spInf18"];
                            $row["spInf23"] = $resultRow_week["spInf23"];
                            $row["spInf27"] = $resultRow_week["spInf27"];
                            $row["spInf28"] = $resultRow_week["spInf28"];
                            $row["spInf29"] = $resultRow_week["spInf29"];
                            $row["spInf30"] = $resultRow_week["spInf30"];
                            $row["spInf37"] = $resultRow_week["spInf37"];
                            $row["spInf38"] = $resultRow_week["spInf38"];
                            $row["spInf40"] = $resultRow_week["spInf40"];

                            array_push($weeks, $row);
                        }
                    }

                    $query_ministry = "SELECT spInf1, spInf3, spInf7, spInf8, spInf9, spInf10";
                    $query_ministry .= " FROM sp_week_min";
                    $query_ministry .= " WHERE spInf1 >=";
                    $query_ministry .= " " . $weekcode;
                    $query_ministry .= " ORDER BY spInf1 ASC";

                    $result_ministry = mysqli_query($database, $query_ministry);

                    if (mysqli_num_rows($result_ministry) > 0) {

                        while ($resultRow_ministry = $result_ministry->fetch_assoc()) {

                            $resultRow_ministry = array_map("utf8_encode", $resultRow_ministry);

                            $row = array();
                            $row["spInf1"] = $resultRow_ministry["spInf1"];
                            $row["spInf3"] = $resultRow_ministry["spInf3"];
                            $row["spInf7"] = $resultRow_ministry["spInf7"];
                            $row["spInf8"] = $resultRow_ministry["spInf8"];
                            $row["spInf9"] = $resultRow_ministry["spInf9"];
                            $row["spInf10"] = $resultRow_ministry["spInf10"];

                            array_push($ministry, $row);
                        }
                    }

                    $query_christians = "SELECT spInf1, spInf6";
                    $query_christians .= " FROM sp_week_cr";
                    $query_christians .= " WHERE spInf1 >=";
                    $query_christians .= " " . $weekcode;
                    $query_christians .= " ORDER BY spInf1 ASC";

                    $result_christians = mysqli_query($database, $query_christians);

                    if (mysqli_num_rows($result_christians) > 0) {

                        while ($resultRow_christians = $result_christians->fetch_assoc()) {

                            $resultRow_christians = array_map("utf8_encode", $resultRow_christians);

                            $row = array();
                            $row["spInf1"] = $resultRow_christians["spInf1"];
                            $row["spInf6"] = $resultRow_christians["spInf6"];

                            array_push($christians, $row);
                        }
                    }
                }

                $result_mem->close();
                $result_week->close();
                $result_ministry->close();
                $result_christians->close();
                mysqli_close($database);

                foreach ($weeks as $week) {

                    $row_weekcode = $week['spInf1'];

                    $year = substr($row_weekcode, 0, 4);
                    $weeknr = substr($row_weekcode, 4, 2);
                    $date = date("Y-m-d", strtotime($year . "W" . $weeknr . "1"));

                    if ($memberID == $week["spInf3"]) {
                        array_push($activities, add_activity($row_weekcode, $date, $language['PRESIDENT_MIDWEEK'], $language['PRESIDENT_MIDWEEK_ICON']));
                    }

                    if ($memberID == $week["spInf4"]) {
                        array_push($activities, add_activity($row_weekcode, $date, $language['PRAY1_MIDWEEK'], $language['PRAY1_MIDWEEK_ICON']));
                    }

                    if ($memberID == $week["spInf11"]) {
                        array_push($activities, add_activity($row_weekcode, $date, $language['TALK_MIDWEEK'], $language['TALK_MIDWEEK_ICON']));
                    }

                    if ($memberID == $week["spInf14"]) {
                        array_push($activities, add_activity($row_weekcode, $date, $language['DIGGING_MIDWEEK'], $language['DIGGING_MIDWEEK_ICON']));
                    }

                    if ($memberID == $week["spInf18"]) {
                        array_push($activities, add_activity($row_weekcode, $date, $language['BIBLE_READING_A'], $language['BIBLE_READING_A_ICON']));
                    }

                    if ($memberID == $week["spInf23"]) {
                        array_push($activities, add_activity($row_weekcode, $date, $language['CONGRBIBLESTUDY_MIDWEEK'], $language['CONGRBIBLESTUDY_MIDWEEK_ICON']));
                    }

                    if ($memberID == $week["spInf27"]) {
                        array_push($activities, add_activity($row_weekcode, $date, $language['PRAY2_MIDWEEK'], $language['PRAY2_MIDWEEK_ICON']));
                    }

                    if ($memberID == $week["spInf28"]) {
                        array_push($activities, add_activity($row_weekcode, $date, $language['BIBLE_READING_B'], $language['BIBLE_READING_B_ICON']));
                    }

                    if ($memberID == $week["spInf29"]) {
                        array_push($activities, add_activity($row_weekcode, $date, $language['READER_CONGRBIBLESTUDY'], $language['READER_CONGRBIBLESTUDY_ICON']));
                    }

                    if ($memberID == $week["spInf30"]) {
                        array_push($activities, add_activity($row_weekcode, $date, $language['PRESIDENT_WEEKEND'], $language['PRESIDENT_WEEKEND_ICON']));
                    }

                    if ($memberID == $week["spInf37"]) {
                        array_push($activities, add_activity($row_weekcode, $date, $language['WATCHTOWER_WEEKEND'], $language['WATCHTOWER_WEEKEND_ICON']));
                    }

                    if ($memberID == $week["spInf38"]) {
                        array_push($activities, add_activity($row_weekcode, $date, $language['READER_WATCHTOWER'], $language['READER_WATCHTOWER_ICON']));
                    }

                    if ($memberID == $week["spInf40"]) {
                        array_push($activities, add_activity($row_weekcode, $date, $language['PRAY2_WEEKEND'], $language['PRAY2_WEEKEND_ICON']));
                    }
                }

                foreach ($ministry as $min) {

                    $row_weekcode = $min['spInf1'];

                    $year = substr($row_weekcode, 0, 4);
                    $weeknr = substr($row_weekcode, 4, 2);
                    $date = date("Y-m-d", strtotime($year . "W" . $weeknr . "1"));

                    if ($memberID == $min["spInf7"]) {
                        array_push($activities, add_activity($row_weekcode, $date, $language['MINISTRY_STUDENT_1'], $language['MINISTRY_STUDENT_1_ICON']));
                    }

                    if ($memberID == $min["spInf8"]) {
                        array_push($activities, add_activity($row_weekcode, $date, $language['MINISTRY_ASSISTANT_1'], $language['MINISTRY_ASSISTANT_1_ICON']));
                    }

                    if ($memberID == $min["spInf9"]) {

                        if ($min["spInf3"] == 1) {
                            array_push($activities, add_activity($row_weekcode, $date, $language['MINISTRY_PRESIDENT'], $president2icon));
                        } else {
                            array_push($activities, add_activity($row_weekcode, $date, $language['MINISTRY_STUDENT_2'], $language['MINISTRY_STUDENT_2_ICON']));
                        }
                    }

                    if ($memberID == $min["spInf10"]) {
                        array_push($activities, add_activity($row_weekcode, $date, $language['MINISTRY_ASSISTANT_2'], $language['MINISTRY_ASSISTANT_2_ICON']));
                    }
                }

                foreach ($christians as $christian) {

                    $row_weekcode = $christian['spInf1'];

                    $year = substr($row_weekcode, 0, 4);
                    $weeknr = substr($row_weekcode, 4, 2);
                    $date = date("Y-m-d", strtotime($year . "W" . $weeknr . "1"));

                    if ($memberID == $christian["spInf6"]) {
                        array_push($activities, add_activity($row_weekcode, $date, $language['CHRISTIAN_LIFE'], $language['CHRISTIAN_LIFE_ICON']));
                    }
                }
                uasort($activities, 'cmp');
            }
        }
    }
}

function add_activity($row_weekcode, $date, $activity_name, $activity_icon)
{
    $activity_array = array();

    $activity_array['weekcode'] = $row_weekcode;
    $activity_array['date'] = $date;
    $activity_array['name'] = $activity_name;
    $activity_array['icon'] = $activity_icon;

    return $activity_array;
}

function cmp($a, $b)
{
    if ($a['weekcode'] == $b['weekcode']) {
        return 0;
    }
    return ($a['weekcode'] < $b['weekcode']) ? - 1 : 1;
}

?>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>SM-Net: SupportPlanner - Monitor</title>
<link rel="stylesheet" type="text/css"
	href="bootstrap-4.3.1-dist/css/bootstrap.css">
<script src="bootstrap-4.3.1-dist/js/bootstrap.js"></script>
</head>
<body class="bg-dark">

	<div class="container-fluid">
		<img src="images/logo.png" class="img-fluid" alt="SupportPlanner Logo">
	</div>

        <?php if (isset ( $language )) { ?>

        <?php if ( empty ( $pwmon )) { ?>


        <table class="table table-striped table-dark">
		<thead>
			<tr>
				<th scope="col" class="text-center"><?php echo $language['error1']?></th>
			</tr>
		</thead>
		<tbody>
		</tbody>
	</table>


        <?php
            } else {
                ?>

        <?php if ( empty ( $memberID)) { ?>

        <table class="table table-striped table-dark">
		<thead>
			<tr>
				<th scope="col"><?php echo $language['error1']?></th>
			</tr>
		</thead>
		<tbody>
		</tbody>
	</table>

        <?php
                } else {

                    $thisweekcount = 0;
                    $nextweekcount = 0;
                    $thisweekheader = 0;
                    $nextweekheader = 0;
                    $thisweeknoactivity = 0;
                    $nextweeknoactivity = 0;

                    ?>

	<!-- TODAY -->
	<br>
	<div class="p-3 mb-2 bg-secondary text-white"><?php echo sprintf($language['todayText'], date($language['todayFormat']));?></div>
	<br>
	
                <?php

                    foreach ($activities as $activity) {

                        $activity_weekcode = $activity['weekcode'];
                        $activity_date = $activity['date'];
                        $activity_name = $activity['name'];
                        $activity_icon = $activity['icon'];

                        if ($thisweekheader == 0) {

                            ?>
                                
    <!-- THIS WEEK -->
	<table class="table table-striped table-dark">
		<thead>
			<tr>
				<th style="width: 48%"><?php echo $language['thisweek'];?></th>
				<th style="width: 4%"></th>
				<th style="width: 48%"><?php echo $language['activity'];?></th>
			</tr>
		</thead>
		<tbody>
                                
                                <?php

                            $thisweekheader = 1;
                        }

                        if ($activity_weekcode == $weekcode) {

                            $thisweekcount = $thisweekcount + 1;
                        } else {

                            $nextweekcount = $nextweekcount + 1;

                            if ($nextweekheader == 0) {

                                if ($thisweekcount == 0) {

                                    if ($thisweeknoactivity == 0) {

                                        ?>
                                    
			<!-- NO ACTIVITY FOR THIS WEEK -->
			<tr>
				<td><?php echo $language['noactivity'];?></td>
				<td></td>
				<td></td>
			</tr>
                                    
                                    <?php

                                        $thisweeknoactivity = 1;
                                    }
                                }

                                ?>
                
                            
    <!-- THIS WEEK CLOSE -->
		</tbody>
	</table>
	<br>

	<!-- NEXT WEEK -->
	<table class="table table-striped table-dark">
		<thead>
			<tr>
				<th style="width: 48%"><?php echo $language['nextweek'];?></th>
				<th style="width: 4%"></th>
				<th style="width: 48%"><?php echo $language['activity'];?></th>
			</tr>
		</thead>
		<tbody>
                            
                            <?php

                                $nextweekheader = 1;
                            }
                        }

                        ?>

            <tr>
				<td><?php echo $activity_date;?></td>
				<td class="text-center"><img
					src="images/<?php echo $activity_icon;?>" width="25" height="25"
					alt="Activity Icon"></td>
				<td><?php echo $activity_name;?></td>
			</tr>

                <?php
                    }

                    // NO ACTIVITY THIS AND NEXT WEEK ?

                    if ($thisweekcount == 0) {

                        if ($thisweekheader == 0) {

                            ?>
                           

	<!-- THIS WEEK HEADER -->
			<table class="table table-striped table-dark">
				<thead>
					<tr>
						<th style="width: 48%"><?php echo $language['thisweek'];?></th>
						<th style="width: 4%"></th>
						<th style="width: 48%"><?php echo $language['activity'];?></th>
					</tr>
				</thead>
				<tbody>
                            
                            <?php
                        }

                        if ($thisweeknoactivity == 0) {

                            ?>
                                    
			<!-- NO ACTIVITY FOR NEXT WEEK -->
					<tr>
						<td><?php echo $language['noactivity'];?></td>
						<td></td>
						<td></td>
					</tr>
                                    
            <?php
                        }

                        $thisweeknoactivity = 1;
                    }

                    
                    // NO ACTIVITY NEXT WEEK?
                    
                    if ($nextweekcount == 0) {

                        if ($nextweekheader == 0) {

                            ?>
                            
        <!-- THIS WEEK CLOSE 2 WITHOUT NEXTWEEK IN LOOP -->
				</tbody>
			</table>
			<br>

			<!-- NEXT WEEK -->
			<table class="table table-striped table-dark">
				<thead>
					<tr>
						<th style="width: 48%"><?php echo $language['nextweek'];?></th>
						<th style="width: 4%"></th>
						<th style="width: 48%"><?php echo $language['activity'];?></th>
					</tr>
				</thead>
				<tbody>
                            
                            <?php
                        }

                        if ($nextweeknoactivity == 0) {

                            ?>
                                    
			<!-- NO ACTIVITY FOR NEXT WEEK -->
					<tr>
						<td><?php echo $language['noactivity'];?></td>
						<td></td>
						<td></td>
					</tr>
                                    
            <?php
                        }

                        $nextweeknoactivity = 1;
                    }
                }

                ?>
            </tbody>
			</table>
        <?php
            }
        }
        ?>
        




</body>
</html>