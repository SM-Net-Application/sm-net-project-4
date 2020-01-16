<?php
// Delete Service Group
if (isset ( $jsonObj ["spSerGrID"] )) {
	if (! empty ( $jsonObj ["spSerGrID"] )) {
		require_once dirname(__DIR__, 1) . '/config.php';
		$database = mysqli_connect ( DB_SERVER, DB_USER, DB_PASSWORD, DB_DATABASE );
		mysqli_set_charset ( $database, 'utf8' );
		if (! $database) {
			$response ["status"] = 4;
			$response ["error"] = mysqli_connect_error ();
		} else {
			$query = "DELETE FROM sp_sergr";
			$query .= " WHERE spSerGrID=" . $jsonObj ["spSerGrID"];
			
			if ($database->query ( $query ) === TRUE) {
				
				$query = "UPDATE sp_fam";
				$query .= " SET spInf6=-1";
				$query .= " WHERE spInf6=" . $jsonObj ["spSerGrID"];
				
				if (! ($database->query ( $query ) === TRUE)) {
					$response ["error"] .= "<Non sono riuscito a rimuovere il gruppoDiServizio dalle famiglie>";
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