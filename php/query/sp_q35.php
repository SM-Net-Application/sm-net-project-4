<?php

// Insert Public Meeting

if (isset ( $jsonObj ["spWeekID"] ) && isset ( $jsonObj ["spInf30"] ) && isset ( $jsonObj ["spInf31"] ) && isset ( $jsonObj ["spInf32"] ) && isset ( $jsonObj ["spInf33"] ) && isset ( $jsonObj ["spInf34"] ) && isset ( $jsonObj ["spInf62"] ) && isset ( $jsonObj ["spInf65"] ) && isset ( $jsonObj ["spInf66"] )) {
	if (! empty ( $jsonObj ["spWeekID"] )) {
		require_once dirname(__DIR__, 1) . '/config.php';
		$database = mysqli_connect ( DB_SERVER, DB_USER, DB_PASSWORD, DB_DATABASE );
		mysqli_set_charset ( $database, 'utf8' );
		if (! $database) {
			$response ["status"] = 4;
			$response ["error"] = mysqli_connect_error ();
		} else {
			$query = "UPDATE sp_week";
			$query .= " SET";
            $query .= " spInf30 = " . $jsonObj ["spInf30"] . ",";
            $query .= " spInf31 = '" . $jsonObj ["spInf31"] . "',";
            $query .= " spInf32 = '" . $jsonObj ["spInf32"] . "',";
            $query .= " spInf33 = '" . $jsonObj ["spInf33"] . "',";
			$query .= " spInf34 = '" . $jsonObj ["spInf34"] . "',";
			$query .= " spInf62 = '" . $jsonObj ["spInf62"] . "',";
			$query .= " spInf65 = " . $jsonObj ["spInf65"] . ",";
			$query .= " spInf66 = " . $jsonObj ["spInf66"];
			$query .= " WHERE spWeekID = " . $jsonObj ["spWeekID"];
			
			if ($database->query ( $query ) === TRUE) {
				$response ["status"] = 0;
			} else {
				$response ["status"] = 4;
				$response ["error"] = $database->error;
			}
			mysqli_close ( $database );
		}
	} else {
		$response ["status"] = 6;
	}
} else {
	$response ["status"] = 6;
}
?>