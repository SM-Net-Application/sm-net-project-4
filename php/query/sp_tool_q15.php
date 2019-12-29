<?php
// Update family
if (isset ( $jsonObj ["spFamID"] ) && isset ( $jsonObj ["spInf1"] ) && isset ( $jsonObj ["spInf2"] ) && isset ( $jsonObj ["spInf3"] ) && isset ( $jsonObj ["spInf4"] ) && isset ( $jsonObj ["spInf5"] ) && isset ( $jsonObj ["spInf7"] ) && isset ( $jsonObj ["spInf8"] ) && isset ( $jsonObj ["idToRemove"] ) && isset ( $jsonObj ["idToSet"] )) {
	if (! empty ( $jsonObj ["spInf1"] )) {
		require_once __DIR__ . '/config.php';
		$database = mysqli_connect ( DB_SERVER, DB_USER, DB_PASSWORD, DB_DATABASE );
		mysqli_set_charset ( $database, 'utf8' );
		if (! $database) {
			$response ["status"] = 4;
			$response ["error"] = mysqli_connect_error ();
		} else {
			$query = "UPDATE sp_fam SET";
			$query .= " spInf1='" . $jsonObj ["spInf1"] . "',";
			$query .= " spInf2='" . $jsonObj ["spInf2"] . "',";
			$query .= " spInf3='" . $jsonObj ["spInf3"] . "',";
			$query .= " spInf4='" . $jsonObj ["spInf4"] . "',";
			$query .= " spInf5='" . $jsonObj ["spInf5"] . "',";
			$query .= " spInf7='" . $jsonObj ["spInf7"] . "',";
			$query .= " spInf8=" . $jsonObj ["spInf8"];
			$query .= " WHERE spFamID=" . $jsonObj ["spFamID"];
			
			if ($database->query ( $query ) === TRUE) {
				
				$spFamID = $jsonObj ["spFamID"];
				
				if (! empty ( $jsonObj ["idToRemove"] )) {
					
					$query = "UPDATE sp_members";
					$query .= " SET spInf5=-1";
					$query .= " WHERE spMemberID IN (" . $jsonObj ["idToRemove"] . ")";
					
					if (! ($database->query ( $query ) === TRUE)) {
						$response ["error"] .= "<Non sono riuscito a rimuovere la famiglia dai componenti>";
					}
				}
				
				if (! empty ( $jsonObj ["idToSet"] )) {
					
					$query = "UPDATE sp_members";
					$query .= " SET spInf5=" . $spFamID;
					$query .= " WHERE spMemberID IN (" . $jsonObj ["idToSet"] . ")";
					
					if (! ($database->query ( $query ) === TRUE)) {
						$response ["error"] .= "<Non sono riuscito a settare la famiglia nei componenti>";
					}
				}
				
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