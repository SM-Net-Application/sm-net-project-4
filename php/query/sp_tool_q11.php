<?php
// Update member
if (isset ( $jsonObj ["spInf1"] ) && isset ( $jsonObj ["spInf2"] ) && isset ( $jsonObj ["spInf3"] ) && isset ( $jsonObj ["spInf4"] ) && isset ( $jsonObj ["spInf6"] ) && isset ( $jsonObj ["spInf7"] ) && isset ( $jsonObj ["spInf8"] ) && isset ( $jsonObj ["spInf9"] ) && isset ( $jsonObj ["spInf10"] ) && isset ( $jsonObj ["spInf11"] ) && isset ( $jsonObj ["spInf12"] ) && isset ( $jsonObj ["spInf13"] ) && isset ( $jsonObj ["spInf14"] ) && isset ( $jsonObj ["spInf15"] ) && isset ( $jsonObj ["spInf16"] ) && isset ( $jsonObj ["spInf17"] ) && isset ( $jsonObj ["spInf18"] ) && isset ( $jsonObj ["spInf19"] ) && isset ( $jsonObj ["spInf20"] ) && isset ( $jsonObj ["spInf21"] ) && isset ( $jsonObj ["spInf22"] ) && isset ( $jsonObj ["spInf23"] ) && isset ( $jsonObj ["spInf24"] ) && isset ( $jsonObj ["spInf25"] ) && isset ( $jsonObj ["spInf26"] ) && isset ( $jsonObj ["spInf27"] ) && isset ( $jsonObj ["spInf28"] ) && isset ( $jsonObj ["spInf29"] )) {
	if (! empty ( $jsonObj ["spInf1"] ) && ! empty ( $jsonObj ["spInf2"] ) && ! empty ( $jsonObj ["spInf3"] )) {
		require_once __DIR__ . '/config.php';
		$database = mysqli_connect ( DB_SERVER, DB_USER, DB_PASSWORD, DB_DATABASE );
		mysqli_set_charset ( $conn, 'utf8' );
		if (! $database) {
			$response ["status"] = 4;
			$response ["error"] = mysqli_connect_error ();
		} else {
			$query = "UPDATE sp_members";
			$query .= " SET spInf1='" . $jsonObj ["spInf1"] . "', ";
			$query .= " spInf2='" . $jsonObj ["spInf2"] . "', ";
			$query .= " spInf3='" . $jsonObj ["spInf3"] . "', ";
			$query .= " spInf4=" . $jsonObj ["spInf4"] . ", ";
			$query .= " spInf6=" . $jsonObj ["spInf6"] . ", ";
			$query .= " spInf7=" . $jsonObj ["spInf7"] . ", ";
			$query .= " spInf8=" . $jsonObj ["spInf8"] . ", ";
			$query .= " spInf9=" . $jsonObj ["spInf9"] . ", ";
			$query .= " spInf10=" . $jsonObj ["spInf10"] . ", ";
			$query .= " spInf11=" . $jsonObj ["spInf11"] . ", ";
			$query .= " spInf12=" . $jsonObj ["spInf12"] . ", ";
			$query .= " spInf13=" . $jsonObj ["spInf13"] . ", ";
			$query .= " spInf14=" . $jsonObj ["spInf14"] . ", ";
			$query .= " spInf15=" . $jsonObj ["spInf15"] . ", ";
			$query .= " spInf16=" . $jsonObj ["spInf16"] . ", ";
			$query .= " spInf17=" . $jsonObj ["spInf17"] . ", ";
			$query .= " spInf18=" . $jsonObj ["spInf18"] . ", ";
			$query .= " spInf19=" . $jsonObj ["spInf19"] . ", ";
			$query .= " spInf20=" . $jsonObj ["spInf20"] . ", ";
			$query .= " spInf21=" . $jsonObj ["spInf21"] . ", ";
			$query .= " spInf22=" . $jsonObj ["spInf22"] . ", ";
			$query .= " spInf23=" . $jsonObj ["spInf23"] . ", ";
			$query .= " spInf24=" . $jsonObj ["spInf24"] . ", ";
			$query .= " spInf25=" . $jsonObj ["spInf25"] . ", ";
			$query .= " spInf26=" . $jsonObj ["spInf26"] . ", ";
			$query .= " spInf27=" . $jsonObj ["spInf27"] . ", ";
			$query .= " spInf28=" . $jsonObj ["spInf28"] . ", ";
			$query .= " spInf29=" . $jsonObj ["spInf29"];
			$query .= " WHERE spMemberID=" . $jsonObj ["spMemberID"];
			
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