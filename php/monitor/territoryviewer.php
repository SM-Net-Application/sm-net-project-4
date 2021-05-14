<?php

$defaultLang = "it.ini";
$langIni = $defaultLang;
$error = "";
$mapsID = "";
$territoryID = "";

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

    if (isset($_GET["tid"])) {
        if (!empty($_GET["tid"])) {

            $territoryID = $_GET["tid"];

            include_once dirname(__DIR__, 1) . '/config.php';

            $database = mysqli_connect(constant("DB_SERVER"), constant("DB_USER"), constant("DB_PASSWORD"), constant("DB_DATABASE"));
            mysqli_set_charset($database, 'utf8');
            if (!$database) {
                $error = "Database connection error: " . mysqli_connect_error();
            } else {

                $query_mem = "SELECT spInf10";
                $query_mem .= " FROM sp_territory";
                $query_mem .= " WHERE spInf31 =";
                $query_mem .= " '" . $territoryID . "'";

                $result_mem = mysqli_query($database, $query_mem);

                if (mysqli_num_rows($result_mem) > 0) {

                    $resultRow_mem = $result_mem->fetch_assoc();
                    $resultRow_mem = array_map("utf8_encode", $resultRow_mem);

                    $mapsID = $resultRow_mem["spInf10"];
                }

                $result_mem->close();

                mysqli_close($database);
            }
        }
    }
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

    <br><br>

    <?php

    if (!isset($language) || empty($territoryID) || empty($mapsID)) {

    ?>

        <table class="table table-striped table-dark">
            <thead>
                <tr>
                    <th scope="col" class="text-center"><?php echo $language['territoryviewererror'] ?></th>
                </tr>
            </thead>
            <tbody>
            </tbody>
        </table>

    <?php

    } else {

    ?>

        <iframe src="https://www.google.com/maps/d/embed?mid=<?php echo $mapsID; ?>" width="100%" height="960"></iframe>

    <?php
    
    }

    ?>

</body>

</html>