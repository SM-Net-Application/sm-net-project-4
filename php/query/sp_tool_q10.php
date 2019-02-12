<?php
// Insert member
if (isset ( $jsonObj ["spInf1"] ) && isset ( $jsonObj ["spInf2"] ) && isset ( $jsonObj ["spInf3"] ) && isset ( $jsonObj ["spInf4"] ) && isset ( $jsonObj ["spInf5"] ) && isset ( $jsonObj ["spInf6"] ) && isset ( $jsonObj ["spInf7"] ) && isset ( $jsonObj ["spInf8"] ) && isset ( $jsonObj ["spInf9"] ) && isset ( $jsonObj ["spInf10"] ) && isset ( $jsonObj ["spInf11"] ) && isset ( $jsonObj ["spInf12"] ) && isset ( $jsonObj ["spInf13"] ) && isset ( $jsonObj ["spInf14"] ) && isset ( $jsonObj ["spInf15"] ) && isset ( $jsonObj ["spInf16"] ) && isset ( $jsonObj ["spInf17"] ) && isset ( $jsonObj ["spInf18"] ) && isset ( $jsonObj ["spInf19"] ) && isset ( $jsonObj ["spInf20"] ) && isset ( $jsonObj ["spInf21"] ) && isset ( $jsonObj ["spInf22"] ) && isset ( $jsonObj ["spInf23"] ) && isset ( $jsonObj ["spInf24"] ) && isset ( $jsonObj ["spInf25"] ) && isset ( $jsonObj ["spInf26"] ) && isset ( $jsonObj ["spInf27"] ) && isset ( $jsonObj ["spInf28"] ) && isset ( $jsonObj ["spInf29"] )) {
	if (! empty ( $jsonObj ["spInf1"] ) && ! empty ( $jsonObj ["spInf2"] ) && ! empty ( $jsonObj ["spInf3"] )) {
		require_once __DIR__ . '/config.php';
		$database = mysqli_connect ( DB_SERVER, DB_USER, DB_PASSWORD, DB_DATABASE );
		mysqli_set_charset ( $conn, 'utf8' );
		if (! $database) {
			$response ["status"] = 4;
			$response ["error"] = mysqli_connect_error ();
		} else {
			$query = "INSERT INTO sp_members (spInf1, spInf2, spInf3, spInf4, spInf5, spInf6, spInf7, spInf8, spInf9, spInf10, spInf11, spInf12, spInf13, spInf14, spInf15, spInf16, spInf17, spInf18, spInf19, spInf20, spInf21, spInf22, spInf23, spInf24, spInf25, spInf26, spInf27, spInf28, spInf29) VALUES (";
			$query .= "'" . $jsonObj ["spInf1"] . "', ";
			$query .= "'" . $jsonObj ["spInf2"] . "', ";
			$query .= "'" . $jsonObj ["spInf3"] . "', ";
			$query .= $jsonObj ["spInf4"] . ", ";
			$query .= $jsonObj ["spInf5"] . ", ";
			$query .= $jsonObj ["spInf6"] . ", ";
			$query .= $jsonObj ["spInf7"] . ", ";
			$query .= $jsonObj ["spInf8"] . ", ";
			$query .= $jsonObj ["spInf9"] . ", ";
			$query .= $jsonObj ["spInf10"] . ", ";
			$query .= $jsonObj ["spInf11"] . ", ";
			$query .= $jsonObj ["spInf12"] . ", ";
			$query .= $jsonObj ["spInf13"] . ", ";
			$query .= $jsonObj ["spInf14"] . ", ";
			$query .= $jsonObj ["spInf15"] . ", ";
			$query .= $jsonObj ["spInf16"] . ", ";
			$query .= $jsonObj ["spInf17"] . ", ";
			$query .= $jsonObj ["spInf18"] . ", ";
			$query .= $jsonObj ["spInf19"] . ", ";
			$query .= $jsonObj ["spInf20"] . ", ";
			$query .= $jsonObj ["spInf21"] . ", ";
			$query .= $jsonObj ["spInf22"] . ", ";
			$query .= $jsonObj ["spInf23"] . ", ";
			$query .= $jsonObj ["spInf24"] . ", ";
			$query .= $jsonObj ["spInf25"] . ", ";
			$query .= $jsonObj ["spInf26"] . ", ";
			$query .= $jsonObj ["spInf27"] . ", ";
			$query .= $jsonObj ["spInf28"] . ", ";
			$query .= $jsonObj ["spInf29"] . ")";
			
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