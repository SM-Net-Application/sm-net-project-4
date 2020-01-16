<?php
// Insert Service Group
if (isset ( $jsonObj ["spInf1"] ) && isset ( $jsonObj ["spInf2"] ) && isset ( $jsonObj ["spInf3"] ) && isset ( $jsonObj ["idToRemove"] ) && isset ( $jsonObj ["idToSet"] )) {
	if (! empty ( $jsonObj ["spInf1"] ) && ! empty ( $jsonObj ["spInf2"] )) {
		require_once dirname(__DIR__, 1) . '/config.php';
		$database = mysqli_connect ( DB_SERVER, DB_USER, DB_PASSWORD, DB_DATABASE );
		mysqli_set_charset ( $database, 'utf8' );
		if (! $database) {
			$response ["status"] = 4;
			$response ["error"] = mysqli_connect_error ();
		} else {
			$query = "INSERT INTO sp_sergr (spInf1, spInf2, spInf3) VALUES (";
			$query .= "'" . $jsonObj ["spInf1"] . "', ";
			$query .= $jsonObj ["spInf2"] . ", ";
			$query .= $jsonObj ["spInf3"] . ")";
			
			if ($database->query ( $query ) === TRUE) {
				
				$query = "SELECT LAST_INSERT_ID() AS spSerGroupID";
				
				$result = mysqli_query ( $database, $query );
				
				if (mysqli_num_rows ( $result ) > 0) {
					
					while ( $resultRow = $result->fetch_assoc () ) {
						$resultRow = array_map ( "utf8_encode", $resultRow );
						$spSerGroupID = $resultRow ["spSerGroupID"];
					}
					
					$result->close ();
					
					if ($spSerGroupID > - 1) {
						
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
							$query .= " SET spInf6=" . $spSerGroupID;
							$query .= " WHERE spFamID IN (" . $jsonObj ["idToSet"] . ")";
							
							if (! ($database->query ( $query ) === TRUE)) {
								$response ["error"] .= "<Non sono riuscito a settare il gruppo alle famiglie>";
							}
						}
						
						$response ["status"] = 0;
					} else {
						$response ["status"] = 4;
						$response ["error"] .= "<Gruppo non inserito>";
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