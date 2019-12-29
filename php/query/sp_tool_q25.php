<?php
// Check Info
if (isset ( $jsonObj ["select"] )) {
	if (! empty ( $jsonObj ["select"] )) {
		require_once __DIR__ . '/config.php';
		$database = mysqli_connect ( DB_SERVER, DB_USER, DB_PASSWORD, DB_DATABASE );
		mysqli_set_charset ( $database, 'utf8' );
		if (! $database) {
			$response ["status"] = 4;
			$response ["error"] = mysqli_connect_error ();
		} else {
			$query = "SELECT keyInf, inf";
			$query .= " FROM sp_info";
			$query .= " WHERE keyInf";
			$query .= " IN (" . $jsonObj ["select"] . ")";
			
			$result = mysqli_query ( $database, $query );
			
			if (mysqli_num_rows ( $result ) > 0) {
				$response ["status"] = 0;
				$response ["result"] = array ();
				while ( $resultRow = $result->fetch_assoc () ) {
					$resultRow = array_map ( "utf8_encode", $resultRow );
					$row = array ();
					$row ["keyInf"] = $resultRow ["keyInf"];
					$row ["inf"] = $resultRow ["inf"];
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