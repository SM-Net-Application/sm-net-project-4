<?php
// Insert family
if (isset ( $jsonObj ["spInf1"] ) && isset ( $jsonObj ["spInf2"] ) && isset ( $jsonObj ["spInf3"] ) && isset ( $jsonObj ["spInf4"] ) && isset ( $jsonObj ["spInf5"] ) && isset ( $jsonObj ["spInf6"] ) && isset ( $jsonObj ["spInf7"] ) && isset ( $jsonObj ["spInf8"] ) && isset ( $jsonObj ["idToRemove"] ) && isset ( $jsonObj ["idToSet"] )) {
	if (! empty ( $jsonObj ["spInf1"] ) && ! empty ( $jsonObj ["spInf2"] ) && ! empty ( $jsonObj ["spInf3"] ) && ! empty ( $jsonObj ["spInf4"] ) && ! empty ( $jsonObj ["spInf5"] )) {
		require_once dirname(__DIR__, 1) . '/config.php';
		$database = mysqli_connect ( DB_SERVER, DB_USER, DB_PASSWORD, DB_DATABASE );
		mysqli_set_charset ( $database, 'utf8' );
		if (! $database) {
			$response ["status"] = 4;
			$response ["error"] = mysqli_connect_error ();
		} else {
			$query = "INSERT INTO sp_fam (spInf1, spInf2, spInf3, spInf4, spInf5, spInf6, spInf7, spInf8) VALUES (";
			$query .= "'" . $jsonObj ["spInf1"] . "', ";
			$query .= "'" . $jsonObj ["spInf2"] . "', ";
			$query .= "'" . $jsonObj ["spInf3"] . "', ";
			$query .= "'" . $jsonObj ["spInf4"] . "', ";
			$query .= "'" . $jsonObj ["spInf5"] . "', ";
			$query .= "'" . $jsonObj ["spInf6"] . "', ";
			$query .= "'" . $jsonObj ["spInf7"] . "', ";
			$query .= $jsonObj ["spInf8"] . ")";
			
			if ($database->query ( $query ) === TRUE) {
				
				$query = "SELECT LAST_INSERT_ID() AS spFamID";
				
				$result = mysqli_query ( $database, $query );
				
				if (mysqli_num_rows ( $result ) > 0) {
					
					while ( $resultRow = $result->fetch_assoc () ) {
						$resultRow = array_map ( "utf8_encode", $resultRow );
						$spFamID = $resultRow ["spFamID"];
					}
					
					$result->close ();
					
					if ($spFamID > - 1) {
						
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
						$response ["error"] .= "<Famiglia non inserita>";
					}
				} else {
					$response ["status"] = 5;
				}
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