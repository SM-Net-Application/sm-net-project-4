<?php

/**
 * Support Planner Monitor
 * php version 7.2.34
 * 
 * @category Category
 * @package  Package
 * @author   SM-Net <admin@sm-netzwerk.com>
 * @license  https://www.gnu.org/licenses/gpl-3.0.txt GNU/GPLv3
 * @version  GIT: @0.11.0@
 * @link     https://sm-netzwerk.com
 */
$defaultLang = "it.ini";
$langIni = $defaultLang;
$pwmon = "";
$weekcode = "";
$error = "";
$memberID = "";

$weeks = array();
$ministry = array();
$christians = array();
$audio = array();
$usciere = array();

$audio_pos1 = "";
$audio_pos2 = "";
$audio_pos3 = "";

$usciere_z1 = "";
$usciere_z2 = "";
$usciere_z3 = "";

$activities = array();
$activities_thisweek = array();
$activities_nextweek = array();

if (isset($_GET["lang"])) {
    if (!empty($_GET["lang"])) {
        $langIni = $_GET["lang"] . ".ini";
    }
}

if (!file_exists("languages/" . $langIni)) {
    $langIni = $defaultLang;
}

if (file_exists("languages/" . $langIni)) {

    $language = parse_ini_file("languages/" . $langIni);

    // Password monitor
    if (isset($_GET["pwmon"])) {
        if (!empty($_GET["pwmon"])) {

            $pwmon = $_GET["pwmon"];

            // OGGI
            $objToday = new DateTime();
            $today_text = dateTimeToText($language, $objToday);

            // DOMENICA
            $objSunday = new DateTime();
            $sunday_day_in_week = $objSunday->format('N');
            if ($sunday_day_in_week != '7') {
                $objSunday->modify('next sunday');
            }
            $sunday_day_in_month = $objSunday->format('j');
            $sunday_month_nr = $objSunday->format('n');
            $sunday_year = $objSunday->format('Y');

            // DOMENICA WEEK CODE
            $sunday_week = $objSunday->format('W');
            $sunday_year_key = $sunday_year;
            if ($sunday_month_nr == 1) {
                if ($sunday_day_in_month < 4) {
                    $sunday_year_key = $sunday_year_key - 1;
                }
            }
            $sunday_weekcode = $sunday_year_key . $sunday_week;

            // Database connection
            // require_once dirname(__DIR__, 1) . '/config.php';
            include_once dirname(__DIR__, 1) . '/config.php';
            // $database = mysqli_connect(DB_SERVER, DB_USER, DB_PASSWORD, DB_DATABASE);
            $database = mysqli_connect(constant("DB_SERVER"), constant("DB_USER"), constant("DB_PASSWORD"), constant("DB_DATABASE"));
            mysqli_set_charset($database, 'utf8');
            if (!$database) {
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

                    // Audio/Video : Nomi postazioni

                    $query_conf = "SELECT  inf FROM sp_conf WHERE keyInf=\"inf9\"";
                    $result_conf = mysqli_query($database, $query_conf);
                    if (mysqli_num_rows($result_conf) > 0) {
                        $resultRow_conf = $result_conf->fetch_assoc();
                        $audio_pos1 = $resultRow_conf["inf"];
                    }

                    $query_conf = "SELECT  inf FROM sp_conf WHERE keyInf=\"inf10\"";
                    $result_conf = mysqli_query($database, $query_conf);
                    if (mysqli_num_rows($result_conf) > 0) {
                        $resultRow_conf = $result_conf->fetch_assoc();
                        $audio_pos2 = $resultRow_conf["inf"];
                    }

                    $query_conf = "SELECT  inf FROM sp_conf WHERE keyInf=\"inf11\"";
                    $result_conf = mysqli_query($database, $query_conf);
                    if (mysqli_num_rows($result_conf) > 0) {
                        $resultRow_conf = $result_conf->fetch_assoc();
                        $audio_pos3 = $resultRow_conf["inf"];
                    }
                    
                    $query_conf = "SELECT  inf FROM sp_conf WHERE keyInf=\"inf12\"";
                    $result_conf = mysqli_query($database, $query_conf);
                    if (mysqli_num_rows($result_conf) > 0) {
                        $resultRow_conf = $result_conf->fetch_assoc();
                        $usciere_z1 = $resultRow_conf["inf"];
                    }

                    $query_conf = "SELECT  inf FROM sp_conf WHERE keyInf=\"inf13\"";
                    $result_conf = mysqli_query($database, $query_conf);
                    if (mysqli_num_rows($result_conf) > 0) {
                        $resultRow_conf = $result_conf->fetch_assoc();
                        $usciere_z2 = $resultRow_conf["inf"];
                    }
                    $query_conf = "SELECT  inf FROM sp_conf WHERE keyInf=\"inf14\"";
                    $result_conf = mysqli_query($database, $query_conf);
                    if (mysqli_num_rows($result_conf) > 0) {
                        $resultRow_conf = $result_conf->fetch_assoc();
                        $usciere_z3 = $resultRow_conf["inf"];
                    }

                    // Weeks

                    $query_week = "SELECT spInf1, spInf3, spInf4, spInf11, spInf14, spInf18, spInf23, spInf27, spInf28,";
                    $query_week .= " spInf29, spInf30, spInf37, spInf38, spInf40, spInf44, spInf45, spInf46, spInf47,";
                    $query_week .= " spInf48, spInf49, spInf58";
                    $query_week .= " FROM sp_week";
                    $query_week .= " WHERE spInf1 >=";
                    $query_week .= " " . $sunday_weekcode;
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
                            $row["spInf44"] = $resultRow_week["spInf44"];
                            $row["spInf45"] = $resultRow_week["spInf45"];
                            $row["spInf46"] = $resultRow_week["spInf46"];
                            $row["spInf47"] = $resultRow_week["spInf47"];
                            $row["spInf48"] = $resultRow_week["spInf48"];
                            $row["spInf49"] = $resultRow_week["spInf49"];
                            $row["spInf58"] = $resultRow_week["spInf58"];

                            array_push($weeks, $row);
                        }
                    }

                    $query_ministry = "SELECT spInf1, spInf3, spInf7, spInf8, spInf9, spInf10";
                    $query_ministry .= " FROM sp_week_min";
                    $query_ministry .= " WHERE spInf1 >=";
                    $query_ministry .= " " . $sunday_weekcode;
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
                    $query_christians .= " " . $sunday_weekcode;
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

                $query_audio = "SELECT spInf1, spInf2, spInf3, spInf4, spInf5, spInf6, spInf7,";
                $query_audio .= " spInf8, spInf9, spInf10, spInf11, spInf12, spInf13";
                $query_audio .= " FROM sp_audio";
                $query_audio .= " WHERE spInf1 >=";
                $query_audio .= " " . $sunday_weekcode;
                $query_audio .= " ORDER BY spInf1 ASC";

                $result_audio = mysqli_query($database, $query_audio);

                if (mysqli_num_rows($result_audio) > 0) {

                    while ($resultRow_audio = $result_audio->fetch_assoc()) {

                        $resultRow_audio = array_map("utf8_encode", $resultRow_audio);

                        $row = array();
                        $row["spInf1"] = $resultRow_audio["spInf1"];
                        $row["spInf2"] = $resultRow_audio["spInf2"];
                        $row["spInf3"] = $resultRow_audio["spInf3"];
                        $row["spInf4"] = $resultRow_audio["spInf4"];
                        $row["spInf5"] = $resultRow_audio["spInf5"];
                        $row["spInf6"] = $resultRow_audio["spInf6"];
                        $row["spInf7"] = $resultRow_audio["spInf7"];
                        $row["spInf8"] = $resultRow_audio["spInf8"];
                        $row["spInf9"] = $resultRow_audio["spInf9"];
                        $row["spInf10"] = $resultRow_audio["spInf10"];
                        $row["spInf11"] = $resultRow_audio["spInf11"];
                        $row["spInf12"] = $resultRow_audio["spInf12"];
                        $row["spInf13"] = $resultRow_audio["spInf13"];

                        array_push($audio, $row);
                    }
                }

                $query_usciere = "SELECT spInf1, spInf2, spInf3, spInf4, spInf5, spInf6, spInf7,";
                $query_usciere .= " spInf8, spInf9, spInf10, spInf11, spInf12, spInf13, spInf14,";
                $query_usciere .= " spInf15, spInf16, spInf17, spInf18, spInf19";
                $query_usciere .= " FROM sp_usc";
                $query_usciere .= " WHERE spInf1 >=";
                $query_usciere .= " " . $sunday_weekcode;
                $query_usciere .= " ORDER BY spInf1 ASC";

                $result_usciere = mysqli_query($database, $query_usciere);

                if (mysqli_num_rows($result_usciere) > 0) {

                    while ($resultRow_usciere = $result_usciere->fetch_assoc()) {

                        $resultRow_usciere = array_map("utf8_encode", $resultRow_usciere);

                        $row = array();
                        $row["spInf1"] = $resultRow_usciere["spInf1"];
                        $row["spInf2"] = $resultRow_usciere["spInf2"];
                        $row["spInf3"] = $resultRow_usciere["spInf3"];
                        $row["spInf4"] = $resultRow_usciere["spInf4"];
                        $row["spInf5"] = $resultRow_usciere["spInf5"];
                        $row["spInf6"] = $resultRow_usciere["spInf6"];
                        $row["spInf7"] = $resultRow_usciere["spInf7"];
                        $row["spInf8"] = $resultRow_usciere["spInf8"];
                        $row["spInf9"] = $resultRow_usciere["spInf9"];
                        $row["spInf10"] = $resultRow_usciere["spInf10"];
                        $row["spInf11"] = $resultRow_usciere["spInf11"];
                        $row["spInf12"] = $resultRow_usciere["spInf12"];
                        $row["spInf13"] = $resultRow_usciere["spInf13"];
                        $row["spInf14"] = $resultRow_usciere["spInf14"];
                        $row["spInf15"] = $resultRow_usciere["spInf15"];
                        $row["spInf16"] = $resultRow_usciere["spInf16"];
                        $row["spInf17"] = $resultRow_usciere["spInf17"];
                        $row["spInf18"] = $resultRow_usciere["spInf18"];
                        $row["spInf19"] = $resultRow_usciere["spInf19"];

                        array_push($usciere, $row);
                    }
                }

                $result_mem->close();
                $result_week->close();
                $result_ministry->close();
                $result_christians->close();
                $result_audio->close();
                $result_usciere->close();
                mysqli_close($database);

                foreach ($weeks as $week) {

                    // WEEKCODE DELLA RIGA DEL DATABASE
                    $row_weekcode = $week['spInf1'];
                    $year = substr($row_weekcode, 0, 4);
                    $weeknr = substr($row_weekcode, 4, 2);

                    // LUNEDI DELLA WEEKCODE
                    $thisMonday = new DateTime();
                    $thisMonday->setISODate($year, $weeknr);

                    // GIORNO ADUNANZA INFRASETTIMANALE
                    $midweek_day = $week["spInf44"] - 1;
                    $midweek_hours = $week["spInf45"];
                    $midweek_minute = $week["spInf46"];

                    $midweek = clone $thisMonday;
                    $midweek->add(new DateInterval("P" . $midweek_day . "D"));
                    $midweek->setTime($midweek_hours, $midweek_minute);
                    $midweek_text = dateTimeToText($language, $midweek);

                    // GIORNO ADUNANZA DEL FINE SETTIMANA
                    $weekend_day = $week["spInf47"] - 1;
                    $weekend_hours = $week["spInf48"];
                    $weekend_minute = $week["spInf49"];

                    $weekend = clone $thisMonday;
                    $weekend->add(new DateInterval("P" . $weekend_day . "D"));
                    $weekend->setTime($weekend_hours, $weekend_minute);
                    $weekend_text = dateTimeToText($language, $weekend);

                    // INCARICHI ADUNANZA INFRASETTIMANALE
                    if ($memberID == $week["spInf3"]) {
                        array_push($activities, newTask($midweek, $midweek_text, $language['midweek_president'], $language['midweek_president_icon'], 0, ""));
                    }
                    if ($memberID == $week["spInf4"]) {
                        array_push($activities, newTask($midweek, $midweek_text, $language['midweek_pray1'], $language['midweek_pray1_icon'], 0, ""));
                    }
                    if ($memberID == $week["spInf11"]) {
                        array_push($activities, newTask($midweek, $midweek_text, $language['midweek_talk'], $language['midweek_talk_icon'], 0, ""));
                    }
                    if ($memberID == $week["spInf14"]) {
                        array_push($activities, newTask($midweek, $midweek_text, $language['midweek_digging'], $language['midweek_digging_icon'], 0, ""));
                    }
                    if ($memberID == $week["spInf18"]) {
                        array_push($activities, newTask($midweek, $midweek_text, $language['midweek_biblereading1'], $language['midweek_biblereading1_icon'], 0, ""));
                    }
                    if ($memberID == $week["spInf28"]) {
                        array_push($activities, newTask($midweek, $midweek_text, $language['midweek_biblereading2'], $language['midweek_biblereading1_icon'], 0, ""));
                    }
                    if ($memberID == $week["spInf23"]) {
                        array_push($activities, newTask($midweek, $midweek_text, $language['midweek_congrbiblestudy'], $language['midweek_congrbiblestudy_icon'], 0, ""));
                    }
                    if ($memberID == $week["spInf29"]) {
                        array_push($activities, newTask($midweek, $midweek_text, $language['midweek_congrbiblestudyreader'], $language['midweek_congrbiblestudyreader_icon'], 0, ""));
                    }
                    if ($memberID == $week["spInf27"]) {
                        array_push($activities, newTask($midweek, $midweek_text, $language['midweek_pray2'], $language['midweek_pray2_icon'], 0, ""));
                    }
                    if ($memberID == $week["spInf58"]) {
                        array_push($activities, newTask($midweek, $midweek_text, $language['midweek_conductor2'], $language['midweek_conductor2_icon'], 0, ""));
                    }

                    // INCARICHI ADUNANZA DEL FINE SETTIMANA
                    if ($memberID == $week["spInf30"]) {
                        if ($week["spInf41"] == 1) {
                            array_push($activities, newTask($weekend, $weekend_text, $language['weekend_pray1'], $language['weekend_pray1_icon'], 0, ""));
                        } else {
                            array_push($activities, newTask($weekend, $weekend_text, $language['weekend_president'], $language['weekend_president_icon'], 0, ""));
                        }
                    }
                    if ($memberID == $week["spInf37"]) {
                        array_push($activities, newTask($weekend, $weekend_text, $language['weekend_watchtower'], $language['weekend_watchtower_icon'], 0, ""));
                    }
                    if ($memberID == $week["spInf38"]) {
                        array_push($activities, newTask($weekend, $weekend_text, $language['weekend_watchtowerreader'], $language['weekend_watchtowerreader_icon'], 0, ""));
                    }
                    if ($memberID == $week["spInf40"]) {
                        array_push($activities, newTask($weekend, $weekend_text, $language['weekend_pray2'], $language['weekend_pray2_icon'], 0, ""));
                    }
                }
                // ***********************************
                // ESERCITIAMOCI PER IL MINISTERO
                // ***********************************

                foreach ($ministry as $min) {

                    // WEEKCODE DELLA RIGA DEL DATABASE
                    $row_weekcode = $min['spInf1'];
                    $year = substr($row_weekcode, 0, 4);
                    $weeknr = substr($row_weekcode, 4, 2);

                    // LUNEDI DELLA WEEKCODE
                    $thisMonday = new DateTime();
                    $thisMonday->setISODate($year, $weeknr);

                    $midweek = clone $thisMonday;
                    $midweek_text = "";
                    $weekend = clone $thisMonday;
                    $weekend_text = "";

                    foreach ($weeks as $week) {
                        if ($week['spInf1'] == $row_weekcode) {

                            // GIORNO ADUNANZA INFRASETTIMANALE
                            $midweek_day = $week["spInf44"] - 1;
                            $midweek_hours = $week["spInf45"];
                            $midweek_minute = $week["spInf46"];

                            $midweek = clone $thisMonday;
                            $midweek->add(new DateInterval("P" . $midweek_day . "D"));
                            $midweek->setTime($midweek_hours, $midweek_minute);
                            $midweek_text = dateTimeToText($language, $midweek);

                            // GIORNO ADUNANZA DEL FINE SETTIMANA
                            $weekend_day = $week["spInf47"] - 1;
                            $weekend_hours = $week["spInf48"];
                            $weekend_minute = $week["spInf49"];

                            $weekend = clone $thisMonday;
                            $weekend->add(new DateInterval("P" . $weekend_day . "D"));
                            $weekend->setTime($weekend_hours, $weekend_minute);
                            $weekend_text = dateTimeToText($language, $weekend);

                            break;
                        }
                    }

                    if ($memberID == $min["spInf7"]) {
                        array_push($activities, newTask($midweek, $midweek_text, $language['midweek_ministrystudent1'], $language['midweek_ministrystudent1_icon'], 0, ""));
                    }
                    if ($memberID == $min["spInf8"]) {
                        array_push($activities, newTask($midweek, $midweek_text, $language['midweek_ministryassistant1'], $language['midweek_ministryassistant1_icon'], 0, ""));
                    }
                    if ($memberID == $min["spInf9"]) {
                        // if ($min["spInf3"] == 1) {
                        //    array_push($activities, newTask($midweek, $midweek_text, $language['midweek_ministrypresident'], $language['midweek_ministrypresident_icon'], 0, ""));
                        // } else {
                        //    array_push($activities, newTask($midweek, $midweek_text, $language['midweek_ministrystudent2'], $language['midweek_ministrystudent2_icon'], 0, ""));
                        // }
                        if ($min["spInf3"] != 1) {
                            array_push($activities, newTask($midweek, $midweek_text, $language['midweek_ministrystudent2'], $language['midweek_ministrystudent2_icon'], 0, ""));
                        }
                    }
                    if ($memberID == $min["spInf10"]) {
                        array_push($activities, newTask($midweek, $midweek_text, $language['midweek_ministryassistant2'], $language['midweek_ministryassistant2_icon'], 0, ""));
                    }
                }

                // ***********************************
                // VITA CRISTIANA
                // ***********************************

                foreach ($christians as $christian) {

                    // WEEKCODE DELLA RIGA DEL DATABASE
                    $row_weekcode = $christian['spInf1'];
                    $year = substr($row_weekcode, 0, 4);
                    $weeknr = substr($row_weekcode, 4, 2);

                    // LUNEDI DELLA WEEKCODE
                    $thisMonday = new DateTime();
                    $thisMonday->setISODate($year, $weeknr);

                    $midweek = clone $thisMonday;
                    $midweek_text = "";
                    $weekend = clone $thisMonday;
                    $weekend_text = "";

                    foreach ($weeks as $week) {
                        if ($week['spInf1'] == $row_weekcode) {

                            // GIORNO ADUNANZA INFRASETTIMANALE
                            $midweek_day = $week["spInf44"] - 1;
                            $midweek_hours = $week["spInf45"];
                            $midweek_minute = $week["spInf46"];

                            $midweek = clone $thisMonday;
                            $midweek->add(new DateInterval("P" . $midweek_day . "D"));
                            $midweek->setTime($midweek_hours, $midweek_minute);
                            $midweek_text = dateTimeToText($language, $midweek);

                            // GIORNO ADUNANZA DEL FINE SETTIMANA
                            $weekend_day = $week["spInf47"] - 1;
                            $weekend_hours = $week["spInf48"];
                            $weekend_minute = $week["spInf49"];

                            $weekend = clone $thisMonday;
                            $weekend->add(new DateInterval("P" . $weekend_day . "D"));
                            $weekend->setTime($weekend_hours, $weekend_minute);
                            $weekend_text = dateTimeToText($language, $weekend);

                            break;
                        }
                    }

                    if ($memberID == $christian["spInf6"]) {
                        array_push($activities, newTask($midweek, $midweek_text, $language['midweek_christianlife'], $language['midweek_christianlife_icon'], 0, ""));
                    }
                }

                // ***********************************
                // AUDIO VIDEO
                // ***********************************

                foreach ($audio as $aud) {

                    // WEEKCODE DELLA RIGA DEL DATABASE
                    $row_weekcode = $aud['spInf1'];
                    $year = substr($row_weekcode, 0, 4);
                    $weeknr = substr($row_weekcode, 4, 2);

                    // LUNEDI DELLA WEEKCODE
                    $thisMonday = new DateTime();
                    $thisMonday->setISODate($year, $weeknr);

                    $midweek = clone $thisMonday;
                    $midweek_text = "";
                    $weekend = clone $thisMonday;
                    $weekend_text = "";

                    foreach ($weeks as $week) {
                        if ($week['spInf1'] == $row_weekcode) {

                            // GIORNO ADUNANZA INFRASETTIMANALE
                            $midweek_day = $week["spInf44"] - 1;
                            $midweek_hours = $week["spInf45"];
                            $midweek_minute = $week["spInf46"];

                            $midweek = clone $thisMonday;
                            $midweek->add(new DateInterval("P" . $midweek_day . "D"));
                            $midweek->setTime($midweek_hours, $midweek_minute);
                            $midweek_text = dateTimeToText($language, $midweek);

                            // GIORNO ADUNANZA DEL FINE SETTIMANA
                            $weekend_day = $week["spInf47"] - 1;
                            $weekend_hours = $week["spInf48"];
                            $weekend_minute = $week["spInf49"];

                            $weekend = clone $thisMonday;
                            $weekend->add(new DateInterval("P" . $weekend_day . "D"));
                            $weekend->setTime($weekend_hours, $weekend_minute);
                            $weekend_text = dateTimeToText($language, $weekend);

                            break;
                        }
                    }

                    if ($memberID == $aud["spInf2"]) {
                        array_push($activities, newTask($midweek, $midweek_text, $language['midweek_audio1'], $language['midweek_audio1_icon'], 1, $audio_pos1));
                    }
                    if ($memberID == $aud["spInf3"]) {
                        array_push($activities, newTask($midweek, $midweek_text, $language['midweek_audio2'], $language['midweek_audio2_icon'], 1, $audio_pos2));
                    }
                    if ($memberID == $aud["spInf4"]) {
                        array_push($activities, newTask($midweek, $midweek_text, $language['midweek_audio3'], $language['midweek_audio3_icon'], 1, $audio_pos3));
                    }
                    if ($memberID == $aud["spInf5"]) {
                        array_push($activities, newTask($midweek, $midweek_text, $language['midweek_audiomic'], $language['midweek_audiomic_icon'], 0, ""));
                    }
                    if ($memberID == $aud["spInf6"]) {
                        array_push($activities, newTask($midweek, $midweek_text, $language['midweek_audiomic'], $language['midweek_audiomic_icon'], 0, ""));
                    }
                    if ($memberID == $aud["spInf7"]) {
                        array_push($activities, newTask($midweek, $midweek_text, $language['midweek_audiomic'], $language['midweek_audiomic_icon'], 0, ""));
                    }
                    if ($memberID == $aud["spInf8"]) {
                        array_push($activities, newTask($weekend, $weekend_text, $language['weekend_audio1'], $language['weekend_audio1_icon'], 1, $audio_pos1));
                    }
                    if ($memberID == $aud["spInf9"]) {
                        array_push($activities, newTask($weekend, $weekend_text, $language['weekend_audio2'], $language['weekend_audio2_icon'], 1, $audio_pos2));
                    }
                    if ($memberID == $aud["spInf10"]) {
                        array_push($activities, newTask($weekend, $weekend_text, $language['weekend_audio3'], $language['weekend_audio3_icon'], 1, $audio_pos3));
                    }
                    if ($memberID == $aud["spInf11"]) {
                        array_push($activities, newTask($weekend, $weekend_text, $language['weekend_audiomic'], $language['weekend_audiomic_icon'], 0, ""));
                    }
                    if ($memberID == $aud["spInf12"]) {
                        array_push($activities, newTask($weekend, $weekend_text, $language['weekend_audiomic'], $language['weekend_audiomic_icon'], 0, ""));
                    }
                    if ($memberID == $aud["spInf13"]) {
                        array_push($activities, newTask($weekend, $weekend_text, $language['weekend_audiomic'], $language['weekend_audiomic_icon'], 0, ""));
                    }
                }

                // ***********************************
                // USCIERE
                // ***********************************

                foreach ($usciere as $usc) {

                    // WEEKCODE DELLA RIGA DEL DATABASE
                    $row_weekcode = $aud['spInf1'];
                    $year = substr($row_weekcode, 0, 4);
                    $weeknr = substr($row_weekcode, 4, 2);

                    // LUNEDI DELLA WEEKCODE
                    $thisMonday = new DateTime();
                    $thisMonday->setISODate($year, $weeknr);

                    $midweek = clone $thisMonday;
                    $midweek_text = "";
                    $weekend = clone $thisMonday;
                    $weekend_text = "";

                    foreach ($weeks as $week) {
                        if ($week['spInf1'] == $row_weekcode) {

                            // GIORNO ADUNANZA INFRASETTIMANALE
                            $midweek_day = $week["spInf44"] - 1;
                            $midweek_hours = $week["spInf45"];
                            $midweek_minute = $week["spInf46"];

                            $midweek = clone $thisMonday;
                            $midweek->add(new DateInterval("P" . $midweek_day . "D"));
                            $midweek->setTime($midweek_hours, $midweek_minute);
                            $midweek_text = dateTimeToText($language, $midweek);

                            // GIORNO ADUNANZA DEL FINE SETTIMANA
                            $weekend_day = $week["spInf47"] - 1;
                            $weekend_hours = $week["spInf48"];
                            $weekend_minute = $week["spInf49"];

                            $weekend = clone $thisMonday;
                            $weekend->add(new DateInterval("P" . $weekend_day . "D"));
                            $weekend->setTime($weekend_hours, $weekend_minute);
                            $weekend_text = dateTimeToText($language, $weekend);

                            break;
                        }
                    }

                    if ($memberID == $usc["spInf2"]) {
                        array_push($activities, newTask($midweek, $midweek_text, $language['midweek_usciere1'], $language['midweek_usciere1_icon'], 1, $usciere_z1));
                    }
                    if ($memberID == $usc["spInf3"]) {
                        array_push($activities, newTask($midweek, $midweek_text, $language['midweek_usciere1'], $language['midweek_usciere1_icon'], 1, $usciere_z1));
                    }
                    if ($memberID == $usc["spInf4"]) {
                        array_push($activities, newTask($midweek, $midweek_text, $language['midweek_usciere1'], $language['midweek_usciere1_icon'], 1, $usciere_z1));
                    }
                    if ($memberID == $usc["spInf5"]) {
                        array_push($activities, newTask($midweek, $midweek_text, $language['midweek_usciere2'], $language['midweek_usciere2_icon'], 1, $usciere_z2));
                    }
                    if ($memberID == $usc["spInf6"]) {
                        array_push($activities, newTask($midweek, $midweek_text, $language['midweek_usciere2'], $language['midweek_usciere2_icon'], 1, $usciere_z2));
                    }
                    if ($memberID == $usc["spInf7"]) {
                        array_push($activities, newTask($midweek, $midweek_text, $language['midweek_usciere2'], $language['midweek_usciere2_icon'], 1, $usciere_z2));
                    }
                    if ($memberID == $usc["spInf8"]) {
                        array_push($activities, newTask($midweek, $midweek_text, $language['midweek_usciere3'], $language['midweek_usciere3_icon'], 1, $usciere_z3));
                    }
                    if ($memberID == $usc["spInf9"]) {
                        array_push($activities, newTask($midweek, $midweek_text, $language['midweek_usciere3'], $language['midweek_usciere3_icon'], 1, $usciere_z3));
                    }
                    if ($memberID == $usc["spInf10"]) {
                        array_push($activities, newTask($midweek, $midweek_text, $language['midweek_usciere3'], $language['midweek_usciere3_icon'], 1, $usciere_z3));
                    }
                    if ($memberID == $usc["spInf11"]) {
                        array_push($activities, newTask($weekend, $weekend_text, $language['weekend_usciere1'], $language['weekend_usciere1_icon'], 1, $usciere_z1));
                    }
                    if ($memberID == $usc["spInf12"]) {
                        array_push($activities, newTask($weekend, $weekend_text, $language['weekend_usciere1'], $language['weekend_usciere1_icon'], 1, $usciere_z1));
                    }
                    if ($memberID == $usc["spInf13"]) {
                        array_push($activities, newTask($weekend, $weekend_text, $language['weekend_usciere1'], $language['weekend_usciere1_icon'], 1, $usciere_z1));
                    }
                    if ($memberID == $usc["spInf14"]) {
                        array_push($activities, newTask($weekend, $weekend_text, $language['weekend_usciere2'], $language['weekend_usciere2_icon'], 1, $usciere_z2));
                    }
                    if ($memberID == $usc["spInf15"]) {
                        array_push($activities, newTask($weekend, $weekend_text, $language['weekend_usciere2'], $language['weekend_usciere2_icon'], 1, $usciere_z2));
                    }
                    if ($memberID == $usc["spInf16"]) {
                        array_push($activities, newTask($weekend, $weekend_text, $language['weekend_usciere2'], $language['weekend_usciere2_icon'], 1, $usciere_z2));
                    }
                    if ($memberID == $usc["spInf17"]) {
                        array_push($activities, newTask($weekend, $weekend_text, $language['weekend_usciere3'], $language['weekend_usciere3_icon'], 1, $usciere_z3));
                    }
                    if ($memberID == $usc["spInf18"]) {
                        array_push($activities, newTask($weekend, $weekend_text, $language['weekend_usciere3'], $language['weekend_usciere3_icon'], 1, $usciere_z3));
                    }
                    if ($memberID == $usc["spInf19"]) {
                        array_push($activities, newTask($weekend, $weekend_text, $language['weekend_usciere3'], $language['weekend_usciere3_icon'], 1, $usciere_z3));
                    }
                }

                uasort($activities, 'taskComp');
            }
        }
    }
}

function langKey1($format, $value)
{
    return sprintf($format, $value);
}

function langKey2($format, $value1, $value2)
{
    return sprintf($format, $value1, $value2);
}

function dateTimeToText($language, $dateTime)
{
    $dateTime_day = $language[langKey1($language['dayFormat'], $dateTime->format('N'))];
    $dateTime_month = $language[langKey1($language['monthFormat'], $dateTime->format('n'))];
    $dateTime_year = $dateTime->format('Y');
    $dateTime_time = langKey2($language['timeFormat'], $dateTime->format('G'), $dateTime->format('i'));

    return $dateTime_day . " " . $dateTime->format('j') . " " . $dateTime_month . " " . $dateTime_year . " " . $dateTime_time;
}

function newTask($date, $date_text, $activity_name, $activity_icon, $inf, $inftext)
{
    $activity_array = array();

    $activity_array['date'] = $date;
    $activity_array['date_text'] = $date_text;
    if($inf==0){
        $activity_array['name'] = $activity_name;
    } else {
        $activity_array['name'] = sprintf($activity_name, $inftext);
    }
    $activity_array['icon'] = $activity_icon;

    return $activity_array;
}

function taskComp($a, $b)
{
    if ($a['date'] == $b['date']) {
        return 0;
    }
    return ($a['date'] < $b['date']) ? -1 : 1;
}

?>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <title>SM-Net: SupportPlanner - Monitor</title>
    <link rel="stylesheet" type="text/css" href="bootstrap-4.3.1-dist/css/bootstrap.css">
    <script src="bootstrap-4.3.1-dist/js/bootstrap.js"></script>
</head>

<body class="bg-dark">

    <div class="container-fluid">
        <img src="images/logo.png" class="img-fluid" alt="SupportPlanner Logo">
    </div>

    <?php

    if (!isset($language) || empty($pwmon) || empty($memberID)) {

    ?>

        <table class="table table-striped table-dark">
            <thead>
                <tr>
                    <th scope="col" class="text-center"><?php echo $language['error1'] ?></th>
                </tr>
            </thead>
            <tbody>
            </tbody>
        </table>

    <?php

    } else {

    ?>

        <!-- TODAY -->
        <br>
        <div class="p-3 mb-2 bg-secondary text-white"><?php echo $today_text; ?></div>
        <br>

        <?php

        foreach ($activities as $activity) {

            if ($activity['date'] > $objSunday) {
                array_push($activities_nextweek, $activity);
            } else {
                array_push($activities_thisweek, $activity);
            }
        }

        ?>

        <!-- THIS WEEK HEADER-->
        <table class="table table-striped table-dark">
            <thead>
                <tr>
                    <th style="width: 48%"><?php echo $language['thisweek']; ?></th>
                    <th style="width: 4%"></th>
                    <th style="width: 48%"><?php echo $language['activity']; ?></th>
                </tr>
            </thead>
            <tbody>

                <?php

                if (empty($activities_thisweek)) {

                ?>

                    <!-- NO ACTIVITY FOR THIS WEEK -->
                    <tr>
                        <td><?php echo $language['noactivity']; ?></td>
                        <td></td>
                        <td></td>
                    </tr>

                    <?php
                } else {

                    foreach ($activities_thisweek as $activity) {

                    ?>

                        <!-- ROW -->
                        <tr>
                            <td><?php echo $activity['date_text']; ?></td>
                            <td class="text-center"><img src="images/<?php echo $activity['icon']; ?>" width="25" height="25" alt="Activity Icon"></td>
                            <td><?php echo $activity['name']; ?></td>
                        </tr>

                <?php
                    }
                }

                ?>

                <!-- CLOSE TABLE THIS WEEK -->
            </tbody>
        </table>
        <br>

        <?php

        if (!empty($activities_nextweek)) {

        ?>

            <!-- NEXT WEEK HEADER-->
            <table class="table table-striped table-dark">
                <thead>
                    <tr>
                        <th style="width: 48%"><?php echo $language['nextweek']; ?></th>
                        <th style="width: 4%"></th>
                        <th style="width: 48%"><?php echo $language['activity']; ?></th>
                    </tr>
                </thead>
                <tbody>

                    <?php

                    foreach ($activities_nextweek as $activity) {

                    ?>

                        <!-- ROW -->
                        <tr>
                            <td><?php echo $activity['date_text']; ?></td>
                            <td class="text-center"><img src="images/<?php echo $activity['icon']; ?>" width="25" height="25" alt="Activity Icon"></td>
                            <td><?php echo $activity['name']; ?></td>
                        </tr>

            <?php
                    }
                }
            }

            ?>

            <!-- CLOSE TABLE THIS WEEK -->
                </tbody>
            </table>
            <br>

</body>

</html>