<?php
// Insert username and password
if (isset ( $jsonObj ["user"] ) && isset ( $jsonObj ["password"] )) {
	if (! empty ( $jsonObj ["user"] ) && ! empty ( $jsonObj ["password"] )) {
		require_once __DIR__ . '/config.php';
		$database = mysqli_connect ( DB_SERVER, DB_USER, DB_PASSWORD, DB_DATABASE );
		mysqli_set_charset ( $database, 'utf8' );
		if (! $database) {
			$response ["status"] = 4;
			$response ["error"] = mysqli_connect_error ();
		} else {
			$query = "INSERT INTO sp_users (spUserName, spUserPassword) VALUES (";
			$query .= "'" . $jsonObj ["user"] . "', ";
			$query .= "'" . $jsonObj ["password"] . "')";
			
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