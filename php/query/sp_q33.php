<?php
// Update family natural disaster
if (isset($jsonObj["spFamID"]) && isset($jsonObj["spInf1"]) && isset($jsonObj["spInf2"]) && isset($jsonObj["spInf3"]) && isset($jsonObj["spInf4"]) && isset($jsonObj["spInf5"]) && isset($jsonObj["spInf7"])) {
    if (! empty($jsonObj["spFamID"]) && ! empty($jsonObj["spInf1"])) {
        require_once dirname(__DIR__, 1) . '/config.php';
        $database = mysqli_connect(DB_SERVER, DB_USER, DB_PASSWORD, DB_DATABASE);
        mysqli_set_charset($database, 'utf8');
        if (! $database) {
            $response["status"] = 4;
            $response["error"] = mysqli_connect_error();
        } else {
            $query = "UPDATE sp_fam SET";
            $query .= " spInf1='" . $jsonObj["spInf1"] . "',";
            $query .= " spInf2='" . $jsonObj["spInf2"] . "',";
            $query .= " spInf3='" . $jsonObj["spInf3"] . "',";
            $query .= " spInf4='" . $jsonObj["spInf4"] . "',";
            $query .= " spInf5='" . $jsonObj["spInf5"] . "',";
            $query .= " spInf7='" . $jsonObj["spInf7"] . "'";
            $query .= " WHERE spFamID=" . $jsonObj["spFamID"];

            if ($database->query($query) === TRUE) {
                $response["status"] = 0;
            } else {
                $response["status"] = 4;
                $response["error"] = $database->error;
            }
            mysqli_close($database);
        }
    } else {
        $response["status"] = 6;
    }
} else {
    $response["status"] = 6;
}
?>