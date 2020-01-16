<?php
// Delete family
if (isset ( $jsonObj ["spFamilyID"] )) {
	if (! empty ( $jsonObj ["spFamilyID"] )) {
		require_once dirname(__DIR__, 1) . '/config.php';
		$database = mysqli_connect ( DB_SERVER, DB_USER, DB_PASSWORD, DB_DATABASE );
		mysqli_set_charset ( $database, 'utf8' );
		if (! $database) {
			$response ["status"] = 4;
			$response ["error"] = mysqli_connect_error ();
		} else {
			$query = "DELETE FROM sp_fam";
			$query .= " WHERE spFamID=" . $jsonObj ["spFamilyID"];
			
			if ($database->query ( $query ) === TRUE) {
				
				$query = "UPDATE sp_members";
				$query .= " SET spInf5=-1";
				$query .= " WHERE spInf5=" . $jsonObj ["spFamilyID"];
				
				if (! ($database->query ( $query ) === TRUE)) {
					$response ["error"] .= "<Non sono riuscito a rimuovere la famiglia dai componenti>";
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