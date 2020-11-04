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

$activities = array();

$format_day = 'day%d';
$format_month = 'month%d';
$format_time = '%02d:%02d';
$today = "";
$today_text = "";
$sunday = "";
$activities_thisweek = array();
$activities_nextweek = array();

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

    /**
     * Bug
     * L'icona del presidente seconda sala non viene riconosciuta
     * Per tale motivo devo salvarla in una variabile ora
     */
    $president2icon = $language['MINISTRY_PRESIDENTICON'];

    // Password monitor
    if (isset($_GET["pwmon"])) {
        if (! empty($_GET["pwmon"])) {

            $pwmon = $_GET["pwmon"];

            $today = time();
            $today_day = date("j", $today); // 1 to 31
            $today_day_key = sprintf($format_day, date("N", $today)); // 1 to 7
            $today_day_text = $language[$today_day_key];
            $today_month = date("n", $today); // 1 to 12
            $today_month_key = sprintf($format_month, $today_month);
            $today_month_text = $language[$today_month_key];
            $today_year = date('Y', $today);                    
            $today_time_text = sprintf($format_time, date('G', $today), date('i', $today));
            $today_text = $today_day_text . " " . $today_day . " " . $today_month_text . " " . $today_year . " " . $today_time_text;

            // sunday -> Questa settimana -> Giorno DOMENICA
            $sunday = strtotime("Next Sunday");

            $day = date("j", $sunday);
            $month = date("n", $sunday);
            $year = date("o", $sunday);

            if ($month == 1) {
                if ($day < 4) {
                    $year = $year - 1;
                }
            }
                
            $week = date("W", $sunday);

            $weekcode = $year . $week;

            // Database connection
            //require_once dirname(__DIR__, 1) . '/config.php';
            include_once dirname(__DIR__, 1) . '/config.php';
            // $database = mysqli_connect(DB_SERVER, DB_USER, DB_PASSWORD, DB_DATABASE);
            $database = mysqli_connect(constant("DB_SERVER"), constant("DB_USER"), constant("DB_PASSWORD"), constant("DB_DATABASE"));
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
                    $query_week .= " spInf29, spInf30, spInf37, spInf38, spInf40, spInf44, spInf45, spInf46, spInf47,";
                    $query_week .= " spInf48, spInf49";
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
                            $row["spInf44"] = $resultRow_week["spInf44"];
                            $row["spInf45"] = $resultRow_week["spInf45"];
                            $row["spInf46"] = $resultRow_week["spInf46"];
                            $row["spInf47"] = $resultRow_week["spInf47"];
                            $row["spInf48"] = $resultRow_week["spInf48"];
                            $row["spInf49"] = $resultRow_week["spInf49"];

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

                    // date -> Settimana del (lunedi)
                    $row_weekcode = $week['spInf1'];
                    $year = substr($row_weekcode, 0, 4);
                    $weeknr = substr($row_weekcode, 4, 2);
                    $date = date("Y-m-d", strtotime($year . "W" . $weeknr . "1"));

                    // Giorno adunanza infrasettimanale
                    $midweek_day = $week["spInf44"];
                    $midweek_day_diff = $midweek_day - 1;
                    $key_day = sprintf($format_day, $midweek_day);
                    $midweek_day_text = $language[$key_day];
                    // Ora adunanza infrasettimanale
                    $midweek_hours = $week["spInf45"];
                    // Minuto adunanza infrasettimanale
                    $midweek_minute = $week["spInf46"];
                    // Orario adunanza infrasettimanale
                    $midweek_time_text = sprintf($format_time, $midweek_hours, $midweek_minute);
                    // Data adunanza infrasettimanale
                    $midweek = date('Y-m-d', strtotime("$date + $midweek_day_diff day"));
                    // Giorno numero nel mese adunanza infrasettimanale
                    $midweek_day_number = date('j', strtotime("$midweek"));
                    // Mese adunanza infrasettimanale
                    $midweek_month = date('n', strtotime("$midweek"));
                    $key_month = sprintf($format_month, $midweek_month);
                    $midweek_month_text = $language[$key_month];
                    // Anno adunanza infrasettimanale
                    $midweek_year = date('Y', strtotime("$midweek"));
                    // Testo data adunanza infrasettimanale
                    $midweek_text = $midweek_day_text . " " . $midweek_day_number . " " . $midweek_month_text . " " . $midweek_year . " " . $midweek_time_text;

                    // Giorno adunanza del fine settimana
                    $weekend_day = $week["spInf47"];
                    $weekend_day_diff = $weekend_day - 1;
                    $key_day = sprintf($format_day, $weekend_day);
                    $weekend_day_text = $language[$key_day];
                    // Ora adunanza del fine settimana
                    $weekend_hours = $week["spInf48"];
                    // Minuto adunanza del fine settimana
                    $weekend_minute = $week["spInf49"];
                    // Orario adunanza del fine settimana
                    $weekend_time_text = sprintf($format_time, $weekend_hours, $weekend_minute);
                    // Data adunanza del fine settimana
                    $weekend = date('Y-m-d', strtotime("$date + $weekend_day_diff day"));
                    // Giorno numero nel mese adunanza del fine settimana
                    $weekend_day_number = date('j', strtotime("$weekend"));
                    // Mese adunanza del fine settimana
                    $weekend_month = date('n', strtotime("$weekend"));
                    $key_month = sprintf($format_month, $weekend_month);
                    $weekend_month_text = $language[$key_month];
                    // Anno adunanza del fine settimana
                    $weekend_year = date('Y', strtotime("$weekend"));
                    // Testo data adunanza del fine settimana
                    $weekend_text = $weekend_day_text . " " . $weekend_day_number . " " . $weekend_month_text . " " . $weekend_year . " " . $weekend_time_text;

                    if ($memberID == $week["spInf3"]) {
                        // array_push($activities, Add_activity($row_weekcode, $date, $language['PRESIDENT_MIDWEEK'], $language['PRESIDENT_MIDWEEK_ICON'], $timestamp, $midweek, $weekend));
                        array_push($activities, Add_Activity_new($midweek, $midweek_text, $language['PRESIDENT_MIDWEEK'], $language['PRESIDENT_MIDWEEK_ICON']));
                    }

                    if ($memberID == $week["spInf4"]) {
                        // array_push($activities, Add_activity($row_weekcode, $date, $language['PRAY1_MIDWEEK'], $language['PRAY1_MIDWEEK_ICON'], $timestamp, $midweek, $weekend));
                        array_push($activities, Add_Activity_new($midweek, $midweek_text, $language['PRAY1_MIDWEEK'], $language['PRAY1_MIDWEEK_ICON']));
                    }

                    if ($memberID == $week["spInf11"]) {
                        // array_push($activities, Add_activity($row_weekcode, $date, $language['TALK_MIDWEEK'], $language['TALK_MIDWEEK_ICON'], $timestamp, $midweek, $weekend));
                        array_push($activities, Add_Activity_new($midweek, $midweek_text, $language['TALK_MIDWEEK'], $language['TALK_MIDWEEK_ICON']));
                    }

                    if ($memberID == $week["spInf14"]) {
                        // array_push($activities, Add_activity($row_weekcode, $date, $language['DIGGING_MIDWEEK'], $language['DIGGING_MIDWEEK_ICON'], $timestamp, $midweek, $weekend));
                        array_push($activities, Add_Activity_new($midweek, $midweek_text, $language['DIGGING_MIDWEEK'], $language['DIGGING_MIDWEEK_ICON']));
                    }

                    if ($memberID == $week["spInf18"]) {
                        // array_push($activities, Add_activity($row_weekcode, $date, $language['BIBLE_READING_A'], $language['BIBLE_READING_A_ICON'], $timestamp, $midweek, $weekend));
                        array_push($activities, Add_Activity_new($midweek, $midweek_text, $language['BIBLE_READING_A'], $language['BIBLE_READING_A_ICON']));
                    }

                    if ($memberID == $week["spInf23"]) {
                        // array_push($activities, Add_activity($row_weekcode, $date, $language['CONGRBIBLESTUDY_MIDWEEK'], $language['CONGRBIBLESTUDY_MIDWEEK_ICON'], $timestamp, $midweek, $weekend));
                        array_push($activities, Add_Activity_new($midweek, $midweek_text, $language['CONGRBIBLESTUDY_MIDWEEK'], $language['CONGRBIBLESTUDY_MIDWEEK_ICON']));
                    }

                    if ($memberID == $week["spInf27"]) {
                        // array_push($activities, Add_activity($row_weekcode, $date, $language['PRAY2_MIDWEEK'], $language['PRAY2_MIDWEEK_ICON'], $timestamp, $midweek, $weekend));
                        array_push($activities, Add_Activity_new($midweek, $midweek_text, $language['PRAY2_MIDWEEK'], $language['PRAY2_MIDWEEK_ICON']));
                    }

                    if ($memberID == $week["spInf28"]) {
                        // array_push($activities, Add_activity($row_weekcode, $date, $language['BIBLE_READING_B'], $language['BIBLE_READING_B_ICON'], $timestamp, $midweek, $weekend));
                        array_push($activities, Add_Activity_new($midweek, $midweek_text, $language['BIBLE_READING_B'], $language['BIBLE_READING_B_ICON']));
                    }

                    if ($memberID == $week["spInf29"]) {
                        // array_push($activities, Add_activity($row_weekcode, $date, $language['READER_CONGRBIBLESTUDY'], $language['READER_CONGRBIBLESTUDY_ICON'], $timestamp, $midweek, $weekend));
                        array_push($activities, Add_Activity_new($midweek, $midweek_text, $language['READER_CONGRBIBLESTUDY'], $language['READER_CONGRBIBLESTUDY_ICON']));
                    }

                    if ($memberID == $week["spInf30"]) {
                        // array_push($activities, Add_activity($row_weekcode, $date, $language['PRESIDENT_WEEKEND'], $language['PRESIDENT_WEEKEND_ICON'], $timestamp, $midweek, $weekend));
                        array_push($activities, Add_Activity_new($weekend, $weekend_text, $language['PRESIDENT_WEEKEND'], $language['PRESIDENT_WEEKEND_ICON']));
                    }

                    if ($memberID == $week["spInf37"]) {
                        // array_push($activities, Add_activity($row_weekcode, $date, $language['WATCHTOWER_WEEKEND'], $language['WATCHTOWER_WEEKEND_ICON'], $timestamp, $midweek, $weekend));
                        array_push($activities, Add_Activity_new($weekend, $weekend_text, $language['WATCHTOWER_WEEKEND'], $language['WATCHTOWER_WEEKEND_ICON']));
                    }

                    if ($memberID == $week["spInf38"]) {
                        // array_push($activities, Add_activity($row_weekcode, $date, $language['READER_WATCHTOWER'], $language['READER_WATCHTOWER_ICON'], $timestamp, $midweek, $weekend));
                        array_push($activities, Add_Activity_new($weekend, $weekend_text, $language['READER_WATCHTOWER'], $language['READER_WATCHTOWER_ICON']));
                    }

                    if ($memberID == $week["spInf40"]) {
                        // array_push($activities, Add_activity($row_weekcode, $date, $language['PRAY2_WEEKEND'], $language['PRAY2_WEEKEND_ICON'], $timestamp, $midweek, $weekend));
                        array_push($activities, Add_Activity_new($weekend, $weekend_text, $language['PRAY2_WEEKEND'], $language['PRAY2_WEEKEND_ICON']));
                    }
                }

                // ***********************************
                // ESERCITIAMOCI PER IL MINISTERO
                // ***********************************

                foreach ($ministry as $min) {

                    $row_weekcode = $min['spInf1'];

                    $year = substr($row_weekcode, 0, 4);
                    $weeknr = substr($row_weekcode, 4, 2);
                    $date = date("Y-m-d", strtotime($year . "W" . $weeknr . "1"));

                    $min_midweek = $date;
                    $min_midweek_text = "";

                    foreach ($weeks as $week) {
                        if ($week['spInf1']==$row_weekcode) {
                            
                            $min_midweek_day=$week['spInf44'];
                            $min_midweek_hour=$week['spInf45'];
                            $min_midweek_minute=$week['spInf46'];

                            $min_midweek_day_diff = $min_midweek_day - 1;
                            $min_midweek_day_key = sprintf($format_day, $min_midweek_day);
                            $min_midweek_day_text = $language[$min_midweek_day_key];
                            
                            $min_midweek_time_text = sprintf($format_time, $min_midweek_hour, $min_midweek_minute);

                            $min_midweek = date('Y-m-d', strtotime("$date + $min_midweek_day_diff day"));

                            $min_midweek_day_number = date('j', strtotime("$min_midweek"));

                            $min_midweek_month = date('n', strtotime("$min_midweek"));
                            $min_midweek_month_key = sprintf($format_month, $min_midweek_month);
                            $min_midweek_month_text = $language[$min_midweek_month_key];
                            
                            $min_midweek_year = date('Y', strtotime("$min_midweek"));
                            
                            $min_midweek_text = $min_midweek_day_text . " " . $min_midweek_day_number . " " . $min_midweek_month_text . " " . $min_midweek_year . " " . $min_midweek_time_text;

                            break;
                        }
                    }

                    if ($memberID == $min["spInf7"]) {
                        //array_push($activities, Add_activity($row_weekcode, $date, $language['MINISTRY_STUDENT_1'], $language['MINISTRY_STUDENT_1_ICON'], $timestamp, $midweek, $weekend));
                        array_push($activities, Add_Activity_new($min_midweek, $min_midweek_text, $language['MINISTRY_STUDENT_1'], $language['MINISTRY_STUDENT_1_ICON']));
                    }

                    if ($memberID == $min["spInf8"]) {
                        //array_push($activities, Add_activity($row_weekcode, $date, $language['MINISTRY_ASSISTANT_1'], $language['MINISTRY_ASSISTANT_1_ICON'], $timestamp, $midweek, $weekend));
                        array_push($activities, Add_Activity_new($min_midweek, $min_midweek_text, $language['MINISTRY_ASSISTANT_1'], $language['MINISTRY_ASSISTANT_1_ICON']));
                    }

                    if ($memberID == $min["spInf9"]) {

                        if ($min["spInf3"] == 1) {
                            // array_push($activities, Add_activity($row_weekcode, $date, $language['MINISTRY_PRESIDENT'], $president2icon, $timestamp, $midweek, $weekend));
                            array_push($activities, Add_Activity_new($min_midweek, $min_midweek_text, $language['MINISTRY_PRESIDENT'], $president2icon));
                        } else {
                            // array_push($activities, Add_activity($row_weekcode, $date, $language['MINISTRY_STUDENT_2'], $language['MINISTRY_STUDENT_2_ICON'], $timestamp, $midweek, $weekend));
                            array_push($activities, Add_Activity_new($min_midweek, $min_midweek_text, $language['MINISTRY_STUDENT_2'], $language['MINISTRY_STUDENT_2_ICON']));
                        }
                    }

                    if ($memberID == $min["spInf10"]) {
                        // array_push($activities, Add_activity($row_weekcode, $date, $language['MINISTRY_ASSISTANT_2'], $language['MINISTRY_ASSISTANT_2_ICON'], $timestamp, $midweek, $weekend));
                        array_push($activities, Add_Activity_new($min_midweek, $min_midweek_text, $language['MINISTRY_ASSISTANT_2'], $language['MINISTRY_ASSISTANT_2_ICON']));
                    }
                }

                // ***********************************
                // VITA CRISTIANA
                // ***********************************

                foreach ($christians as $christian) {

                    $row_weekcode = $christian['spInf1'];

                    $year = substr($row_weekcode, 0, 4);
                    $weeknr = substr($row_weekcode, 4, 2);
                    $date = date("Y-m-d", strtotime($year . "W" . $weeknr . "1"));

                    $cr_midweek = $date;
                    $cr_midweek_text = "";

                    foreach ($weeks as $week) {
                        if ($week['spInf1']==$row_weekcode) {
                            
                            $cr_midweek_day=$week['spInf44'];
                            $cr_midweek_hour=$week['spInf45'];
                            $cr_midweek_minute=$week['spInf46'];

                            $cr_midweek_day_diff = $cr_midweek_day - 1;
                            $cr_midweek_day_key = sprintf($format_day, $cr_midweek_day);
                            $cr_midweek_day_text = $language[$cr_midweek_day_key];
                            
                            $cr_midweek_time_text = sprintf($format_time, $cr_midweek_hour, $cr_midweek_minute);

                            $cr_midweek = date('Y-m-d', strtotime("$date + $cr_midweek_day_diff day"));

                            $cr_midweek_day_number = date('j', strtotime("$cr_midweek"));

                            $cr_midweek_month = date('n', strtotime("$cr_midweek"));
                            $cr_midweek_month_key = sprintf($format_month, $cr_midweek_month);
                            $cr_midweek_month_text = $language[$cr_midweek_month_key];
                            
                            $cr_midweek_year = date('Y', strtotime("$cr_midweek"));
                            
                            $cr_midweek_text = $cr_midweek_day_text . " " . $cr_midweek_day_number . " " . $cr_midweek_month_text . " " . $cr_midweek_year . " " . $cr_midweek_time_text;

                            break;
                        }
                    }

                    if ($memberID == $christian["spInf6"]) {
                        // array_push($activities, Add_activity($row_weekcode, $date, $language['CHRISTIAN_LIFE'], $language['CHRISTIAN_LIFE_ICON'], $timestamp, $midweek, $weekend));
                        array_push($activities, Add_Activity_new($cr_midweek, $cr_midweek_text, $language['CHRISTIAN_LIFE'], $language['CHRISTIAN_LIFE_ICON']));
                    }
                }

                uasort($activities, 'Cmp_new');
            }
        }
    }
}

/**
 * Add activity
 * 
 * @param string $row_weekcode  Weekcode
 * @param date   $date          Weekcode
 * @param string $activity_name Weekcode
 * @param string $activity_icon Weekcode
 * @param date   $sunday        Weekcode
 * @param date   $midweek       Weekcode
 * @param date   $weekend       Weekcode
 * 
 * @return activity_array
 */
function Add_activity($row_weekcode, $date, $activity_name, $activity_icon, $sunday, $midweek, $weekend)
{
    $activity_array = array();

    $activity_array['weekcode'] = $row_weekcode;
    $activity_array['date'] = $date;
    $activity_array['name'] = $activity_name;
    $activity_array['icon'] = $activity_icon;

    $activity_array['sunday'] = $sunday;
    $activity_array['midweek'] = $midweek;
    $activity_array['weekend'] = $weekend;

    return $activity_array;
}

/**
 * Add activity (NEW)
 * 
 * @param date   $date          Weekcode
 * @param string $date_text     Weekcode
 * @param string $activity_name Weekcode
 * @param string $activity_icon Weekcode
 * 
 * @return activity_array
 */
function Add_Activity_new($date, $date_text, $activity_name, $activity_icon)
{
    $activity_array = array();

    $activity_array['date'] = $date;
    $activity_array['date_text'] = $date_text;
    $activity_array['name'] = $activity_name;
    $activity_array['icon'] = $activity_icon;

    return $activity_array;
}

/**
 * Compare
 * 
 * @param string $a Weekcode A
 * @param string $b Weekcode B
 * 
 * @return compare_result
 */
function cmp($a, $b)
{
    if ($a['weekcode'] == $b['weekcode']) {
        return 0;
    }
    return ($a['weekcode'] < $b['weekcode']) ? - 1 : 1;
}

/**
 * Compare (New)
 * 
 * @param string $a Date A
 * @param string $b Date B
 * 
 * @return compare_result
 */
function Cmp_new($a, $b)
{
    if ($a['date'] == $b['date']) {
        return 0;
    }
    return ($a['date'] < $b['date']) ? - 1 : 1;
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

        <?php
            
        if (!isset($language) || empty($pwmon) || empty($memberID)) {

            ?>

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

<!-- TODAY -->
<br>
<div class="p-3 mb-2 bg-secondary text-white"><?php echo $today_text;?></div>
<br>

                    <?php

                    $todayObj = new DateTime($today);
                    $todayObj->setTime(23, 59);

                    foreach ($activities as $activity) {

                        if (strtotime($activity['date'])>$sunday) {
                            array_push($activities_nextweek, $activity);
                        } else {
                            if (!(strtotime($activity['date'])<$todayObj)) {
                                array_push($activities_thisweek, $activity);
                            }
                        }

                    }
                            
                    ?>
                                
<!-- THIS WEEK HEADER-->
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

                    if (empty($activities_thisweek)) {
                        
                        ?>
                        
<!-- NO ACTIVITY FOR THIS WEEK -->
<tr>
<td><?php echo $language['noactivity'];?></td>
<td></td>
<td></td>
</tr>

                        <?php

                    } else {

                        foreach ($activities_thisweek as $activity) {

                            ?>

<!-- ROW -->
<tr>
<td><?php echo $activity['date_text'];?></td>
<td class="text-center"><img src="images/<?php echo $activity['icon'];?>" width="25" height="25" alt="Activity Icon"></td>
<td><?php echo $activity['name'];?></td>
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
<th style="width: 48%"><?php echo $language['nextweek'];?></th>
<th style="width: 4%"></th>
<th style="width: 48%"><?php echo $language['activity'];?></th>
</tr>
</thead>
<tbody>

                        <?php

                        foreach ($activities_nextweek as $activity) {

                            ?>

<!-- ROW -->
<tr>
<td><?php echo $activity['date_text'];?></td>
<td class="text-center"><img src="images/<?php echo $activity['icon'];?>" width="25" height="25" alt="Activity Icon"></td>
<td><?php echo $activity['name'];?></td>
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