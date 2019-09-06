<?php
// Update member natural disaster
if (isset($jsonObj["spMemberID"]) && isset($jsonObj["spInf40"]) && isset($jsonObj["spInf41"])) {
    if (! empty($jsonObj["spMemberID"])) {
        require_once __DIR__ . '/config.php';
        $database = mysqli_connect(DB_SERVER, DB_USER, DB_PASSWORD, DB_DATABASE);
        mysqli_set_charset($conn, 'utf8');
        if (! $database) {
            $response["status"] = 4;
            $response["error"] = mysqli_connect_error();
        } else {
            $query = "UPDATE sp_members";
            $query .= " SET";
            $query .= " spInf40='" . $jsonObj["spInf40"] . "',";
            $query .= " spInf41='" . $jsonObj["spInf41"] . "'";
            $query .= " WHERE spMemberID=" . $jsonObj["spMemberID"];

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