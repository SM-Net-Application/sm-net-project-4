<?php
// Get all circuit overseer weeks
if (isset ( $jsonObj ["keyStart"] ) && isset ( $jsonObj ["keyEnd"] )) {
	if (! empty ( $jsonObj ["keyStart"] ) && ! empty ( $jsonObj ["keyEnd"] )) {
		require_once __DIR__ . '/config.php';
		$database = mysqli_connect ( DB_SERVER, DB_USER, DB_PASSWORD, DB_DATABASE );
		mysqli_set_charset ( $conn, 'utf8' );
		if (! $database) {
			$response ["status"] = 4;
			$response ["error"] = mysqli_connect_error ();
		} else {
			$query = "SELECT spWeekOvID, spInf1, spInf2, spInf3, spInf4, spInf5,";
			$query .= "spInf6, spInf7, spInf8, spInf9, spInf10, spInf11, spInf12,";
			$query .= "spInf13, spInf14, spInf15";
			$query .= " FROM sp_week_ov";
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
					$row ["spWeekOvID"] = $resultRow ["spWeekOvID"];
					$row ["spInf1"] = $resultRow ["spInf1"];
					$row ["spInf2"] = $resultRow ["spInf2"];
					$row ["spInf3"] = $resultRow ["spInf3"];
					$row ["spInf4"] = $resultRow ["spInf4"];
					$row ["spInf5"] = $resultRow ["spInf5"];
					$row ["spInf6"] = $resultRow ["spInf6"];
					$row ["spInf7"] = $resultRow ["spInf7"];
					$row ["spInf8"] = $resultRow ["spInf8"];
					$row ["spInf9"] = $resultRow ["spInf9"];
					$row ["spInf10"] = $resultRow ["spInf10"];
					$row ["spInf11"] = $resultRow ["spInf11"];
					$row ["spInf12"] = $resultRow ["spInf12"];
					$row ["spInf13"] = $resultRow ["spInf13"];
					$row ["spInf14"] = $resultRow ["spInf14"];
					$row ["spInf15"] = $resultRow ["spInf15"];
					
					// Carico la riga nel risultato
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