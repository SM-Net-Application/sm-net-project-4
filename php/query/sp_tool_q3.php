<?php
if (isset ( $jsonObj ["user"] ) && isset ( $jsonObj ["password"] )) {
	if (! empty ( $jsonObj ["user"] ) && ! empty ( $jsonObj ["password"] )) {
		require_once __DIR__ . '/config.php';
		$database = mysqli_connect ( DB_SERVER, DB_USER, DB_PASSWORD, DB_DATABASE );
		mysqli_set_charset ( $conn, 'utf8' );
		if (! $database) {
			$response ["status"] = 4;
			$response ["error"] = mysqli_connect_error ();
		} else {
			$query = "SELECT spUserName, spUserPassword FROM sp_users WHERE";
			$query .= " ";
			$query .= "spUserName='" . $jsonObj ["user"] . "'";
			$query .= " AND ";
			$query .= "spUserPassword='" . $jsonObj ["password"] . "'";
			
			$result = mysqli_query ( $database, $query );
			
			if (mysqli_num_rows ( $result ) > 0) {
				$response ["status"] = 0;
				$response ["result"] = array ();
				while ( $resultRow = $result->fetch_assoc () ) {
					$resultRow = array_map ( "utf8_encode", $resultRow );
					$row = array ();
					$row ["spUserName"] = $resultRow ["spUserName"];
					$row ["spUserPassword"] = $resultRow ["spUserPassword"];
					array_push ( $response ["result"], $row );
				}
			} else {
				$response ["status"] = 5;
			}
			$result->close ();
			mysqli_close ( $database );
		}
	} else {
		$response ["status"] = 6;
	}
} else {
	$response ["status"] = 6;
}
?>