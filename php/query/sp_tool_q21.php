<?php
// Get all weeks
if (isset ( $jsonObj ["keyStart"] ) && isset ( $jsonObj ["keyEnd"] )) {
	if (! empty ( $jsonObj ["keyStart"] ) && ! empty ( $jsonObj ["keyEnd"] )) {
		require_once __DIR__ . '/config.php';
		$database = mysqli_connect ( DB_SERVER, DB_USER, DB_PASSWORD, DB_DATABASE );
		mysqli_set_charset ( $conn, 'utf8' );
		if (! $database) {
			$response ["status"] = 4;
			$response ["error"] = mysqli_connect_error ();
		} else {
			$query = "SELECT spWeekID, spInf1, spInf2";
			$query .= " FROM sp_week";
			$query .= " WHERE spInf1 BETWEEN";
			$query .= " " . $jsonObj ["keyStart"];
			$query .= " AND";
			$query .= " " . $jsonObj ["keyEnd"];
			
			$result = mysqli_query ( $database, $query );
			
			if (mysqli_num_rows ( $result ) > 0) {
				$response ["status"] = 0;
				$response ["result"] = array ();
				while ( $resultRow = $result->fetch_assoc () ) {
					$resultRow = array_map ( "utf8_encode", $resultRow );
					$row = array ();
					$row ["spWeekID"] = $resultRow ["spWeekID"];
					$row ["spInf1"] = $resultRow ["spInf1"];
					$row ["spInf2"] = $resultRow ["spInf2"];
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