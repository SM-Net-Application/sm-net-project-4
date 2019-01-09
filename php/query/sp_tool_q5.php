<?php
// Update user rules
if (isset ( $jsonObj ["spUserID"] ) && isset ( $jsonObj ["spRoleAdmin"] )) {
	if (! empty ( $jsonObj ["spUserID"] )) {
		require_once __DIR__ . '/config.php';
		$database = mysqli_connect ( DB_SERVER, DB_USER, DB_PASSWORD, DB_DATABASE );
		mysqli_set_charset ( $conn, 'utf8' );
		if (! $database) {
			$response ["status"] = 4;
			$response ["error"] = mysqli_connect_error ();
		} else {
			$query = "UPDATE sp_users SET";
			$query .= " spRoleAdmin=" . $jsonObj ["spRoleAdmin"];
			$query .= " WHERE spUserID=" . $jsonObj ["spUserID"];
			
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