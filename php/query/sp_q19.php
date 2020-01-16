<?php
// Update Service Group
if (isset ( $jsonObj ["spSerGrID"] ) && isset ( $jsonObj ["spInf1"] ) && isset ( $jsonObj ["spInf2"] ) && isset ( $jsonObj ["spInf3"] ) && isset ( $jsonObj ["idToRemove"] ) && isset ( $jsonObj ["idToSet"] )) {
	if (! empty ( $jsonObj ["spInf1"] )) {
		require_once dirname(__DIR__, 1) . '/config.php';
		$database = mysqli_connect ( DB_SERVER, DB_USER, DB_PASSWORD, DB_DATABASE );
		mysqli_set_charset ( $database, 'utf8' );
		if (! $database) {
			$response ["status"] = 4;
			$response ["error"] = mysqli_connect_error ();
		} else {
			$query = "UPDATE sp_sergr SET";
			$query .= " spInf1='" . $jsonObj ["spInf1"] . "',";
			$query .= " spInf2=" . $jsonObj ["spInf2"] . ",";
			$query .= " spInf3=" . $jsonObj ["spInf3"];
			$query .= " WHERE spSerGrID=" . $jsonObj ["spSerGrID"];
			
			if ($database->query ( $query ) === TRUE) {
				
				$spSerGrID = $jsonObj ["spSerGrID"];
				
				if (! empty ( $jsonObj ["idToRemove"] )) {
					
					$query = "UPDATE sp_fam";
					$query .= " SET spInf6=-1";
					$query .= " WHERE spFamID IN (" . $jsonObj ["idToRemove"] . ")";
					
					if (! ($database->query ( $query ) === TRUE)) {
						$response ["error"] .= "<Non sono riuscito a rimuovere il gruppo dalle famiglie>";
					}
				}
				
				if (! empty ( $jsonObj ["idToSet"] )) {
					
					$query = "UPDATE sp_fam";
					$query .= " SET spInf6=" . $spSerGrID;
					$query .= " WHERE spFamID IN (" . $jsonObj ["idToSet"] . ")";
					
					if (! ($database->query ( $query ) === TRUE)) {
						$response ["error"] .= "<Non sono riuscito a settare il gruppo alle famiglie>";
					}
				}
				
				$response ["status"] = 0;
			} else {
				$response ["status"] = 4;
				$response ["error"] = $database->error;
			}
			
			// $result->close ();
			mysqli_close ( $database );
		}
	} else {
		$response ["status"] = 6;
	}
} else {
	$response ["status"] = 6;
}
?>