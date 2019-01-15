<?php
// Update member
if (isset ( $jsonObj ["spInf1"] ) && isset ( $jsonObj ["spInf2"] ) && isset ( $jsonObj ["spInf3"] ) && isset ( $jsonObj ["spInf4"] )) {
	if (! empty ( $jsonObj ["spInf1"] ) && ! empty ( $jsonObj ["spInf2"] ) && ! empty ( $jsonObj ["spInf3"] )) {
		require_once __DIR__ . '/config.php';
		$database = mysqli_connect ( DB_SERVER, DB_USER, DB_PASSWORD, DB_DATABASE );
		mysqli_set_charset ( $conn, 'utf8' );
		if (! $database) {
			$response ["status"] = 4;
			$response ["error"] = mysqli_connect_error ();
		} else {
			$query = "UPDATE sp_members";
			$query .= " SET spInf1='" . $jsonObj ["spInf1"] . "', ";
			$query .= " spInf2='" . $jsonObj ["spInf2"] . "', ";
			$query .= " spInf3='" . $jsonObj ["spInf3"] . "', ";
			$query .= " spInf4=" . $jsonObj ["spInf4"];
			$query .= " WHERE spMemberID=" . $jsonObj ["spMemberID"];
			
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