<?php
// Insert Week
if (isset ( $jsonObj ["spInf1"] ) && isset ( $jsonObj ["spInf2"] ) && isset ( $jsonObj ["spInf3"] ) && isset ( $jsonObj ["spInf4"] ) && isset ( $jsonObj ["spInf5"] ) && isset ( $jsonObj ["spInf6"] ) && isset ( $jsonObj ["spInf7"] ) && isset ( $jsonObj ["spInf8"] ) && isset ( $jsonObj ["spInf9"] ) && isset ( $jsonObj ["spInf10"] ) && isset ( $jsonObj ["spInf11"] ) && isset ( $jsonObj ["spInf12"] ) && isset ( $jsonObj ["spInf13"] ) && isset ( $jsonObj ["spInf14"] ) && isset ( $jsonObj ["spInf15"] ) && isset ( $jsonObj ["spInf16"] ) && isset ( $jsonObj ["spInf17"] ) && isset ( $jsonObj ["spInf18"] ) && isset ( $jsonObj ["spInf19"] ) && isset ( $jsonObj ["spInf20"] ) && isset ( $jsonObj ["spInf21"] ) && isset ( $jsonObj ["spInf22"] ) && isset ( $jsonObj ["spInf23"] ) && isset ( $jsonObj ["spInf24"] ) && isset ( $jsonObj ["spInf25"] ) && isset ( $jsonObj ["spInf26"] ) && isset ( $jsonObj ["spInf27"] )) {
	if (! empty ( $jsonObj ["spInf1"] ) && ! empty ( $jsonObj ["spInf2"] ) && ! empty ( $jsonObj ["spInf5"] ) && ! empty ( $jsonObj ["spInf6"] ) && ! empty ( $jsonObj ["spInf7"] ) && ! empty ( $jsonObj ["spInf8"] ) && ! empty ( $jsonObj ["spInf9"] ) && ! empty ( $jsonObj ["spInf10"] ) && ! empty ( $jsonObj ["spInf12"] ) && ! empty ( $jsonObj ["spInf13"] ) && ! empty ( $jsonObj ["spInf15"] ) && ! empty ( $jsonObj ["spInf16"] ) && ! empty ( $jsonObj ["spInf17"] ) && ! empty ( $jsonObj ["spInf19"] ) && ! empty ( $jsonObj ["spInf20"] ) && ! empty ( $jsonObj ["spInf21"] ) && ! empty ( $jsonObj ["spInf22"] ) && ! empty ( $jsonObj ["spInf24"] ) && ! empty ( $jsonObj ["spInf25"] ) && ! empty ( $jsonObj ["spInf26"] )) {
		require_once __DIR__ . '/config.php';
		$database = mysqli_connect ( DB_SERVER, DB_USER, DB_PASSWORD, DB_DATABASE );
		mysqli_set_charset ( $conn, 'utf8' );
		if (! $database) {
			$response ["status"] = 4;
			$response ["error"] = mysqli_connect_error ();
		} else {
			$query = "INSERT INTO sp_week (spInf1, spInf2, spInf3, spInf4, spInf5,";
			$query .= "spInf6, spInf7, spInf8, spInf9, spInf10, spInf11, spInf12,";
			$query .= "spInf13, spInf14, spInf15, spInf16, spInf17, spInf18, spInf19,";
			$query .= "spInf20, spInf21, spInf22, spInf23, spInf24, spInf25, spInf26, spInf27)";
			$query .= "VALUES (";
			$query .= $jsonObj ["spInf1"];
			$query .= ", ";
			$query .= $jsonObj ["spInf2"];
			$query .= ", ";
			$query .= $jsonObj ["spInf3"];
			$query .= ", ";
			$query .= $jsonObj ["spInf4"];
			$query .= ", ";
			$query .= "'" . $jsonObj ["spInf5"] . "'";
			$query .= ", ";
			$query .= "'" . $jsonObj ["spInf6"] . "'";
			$query .= ", ";
			$query .= "'" . $jsonObj ["spInf7"] . "'";
			$query .= ", ";
			$query .= "'" . $jsonObj ["spInf8"] . "'";
			$query .= ", ";
			$query .= "'" . $jsonObj ["spInf9"] . "'";
			$query .= ", ";
			$query .= "'" . $jsonObj ["spInf10"] . "'";
			$query .= ", ";
			$query .= $jsonObj ["spInf11"];
			$query .= ", ";
			$query .= "'" . $jsonObj ["spInf12"] . "'";
			$query .= ", ";
			$query .= "'" . $jsonObj ["spInf13"] . "'";
			$query .= ", ";
			$query .= $jsonObj ["spInf14"];
			$query .= ", ";
			$query .= "'" . $jsonObj ["spInf15"] . "'";
			$query .= ", ";
			$query .= "'" . $jsonObj ["spInf16"] . "'";
			$query .= ", ";
			$query .= "'" . $jsonObj ["spInf17"] . "'";
			$query .= ", ";
			$query .= $jsonObj ["spInf18"];
			$query .= ", ";
			$query .= "'" . $jsonObj ["spInf19"] . "'";
			$query .= ", ";
			$query .= "'" . $jsonObj ["spInf20"] . "'";
			$query .= ", ";
			$query .= "'" . $jsonObj ["spInf21"] . "'";
			$query .= ", ";
			$query .= "'" . $jsonObj ["spInf22"] . "'";
			$query .= ", ";
			$query .= $jsonObj ["spInf23"];
			$query .= ", ";
			$query .= "'" . $jsonObj ["spInf24"] . "'";
			$query .= ", ";
			$query .= "'" . $jsonObj ["spInf25"] . "'";
			$query .= ", ";
			$query .= "'" . $jsonObj ["spInf26"] . "'";
			$query .= ", ";
			$query .= $jsonObj ["spInf27"];
			$query .= ")";
			
			if ($database->query ( $query ) === TRUE) {
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