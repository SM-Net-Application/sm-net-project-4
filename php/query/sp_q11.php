<?php
// Update member
if (isset ( $jsonObj ["spInf1"] ) && isset ( $jsonObj ["spInf2"] ) && isset ( $jsonObj ["spInf3"] ) && isset ( $jsonObj ["spInf4"] ) && isset ( $jsonObj ["spInf6"] ) && isset ( $jsonObj ["spInf7"] ) && isset ( $jsonObj ["spInf8"] ) && isset ( $jsonObj ["spInf9"] ) && isset ( $jsonObj ["spInf10"] ) && isset ( $jsonObj ["spInf11"] ) && isset ( $jsonObj ["spInf12"] ) && isset ( $jsonObj ["spInf13"] ) && isset ( $jsonObj ["spInf14"] ) && isset ( $jsonObj ["spInf15"] ) && isset ( $jsonObj ["spInf16"] ) && isset ( $jsonObj ["spInf17"] ) && isset ( $jsonObj ["spInf18"] ) && isset ( $jsonObj ["spInf19"] ) && isset ( $jsonObj ["spInf20"] ) && isset ( $jsonObj ["spInf21"] ) && isset ( $jsonObj ["spInf22"] ) && isset ( $jsonObj ["spInf23"] ) && isset ( $jsonObj ["spInf24"] ) && isset ( $jsonObj ["spInf25"] ) && isset ( $jsonObj ["spInf26"] ) && isset ( $jsonObj ["spInf27"] ) && isset ( $jsonObj ["spInf28"] ) && isset ( $jsonObj ["spInf29"] ) && isset ( $jsonObj ["spInf30"] ) && isset ( $jsonObj ["spInf31"] ) && isset ( $jsonObj ["spInf32"] ) && isset ( $jsonObj ["spInf33"] ) && isset ( $jsonObj ["spInf34"] ) && isset ( $jsonObj ["spInf35"] ) && isset ( $jsonObj ["spInf36"] ) && isset ( $jsonObj ["spInf37"] ) && isset ( $jsonObj ["spInf38"] ) && isset ( $jsonObj ["spInf39"] ) && isset ( $jsonObj ["spInf40"] ) && isset ( $jsonObj ["spInf41"] ) && isset ( $jsonObj ["spInf42"] ) && isset ( $jsonObj ["spInf43"] ) && isset ( $jsonObj ["spInf44"] ) && isset ( $jsonObj ["spInf45"] ) && isset ( $jsonObj ["spInf46"] ) && isset ( $jsonObj ["spInf47"] ) && isset ( $jsonObj ["spInf48"] ) && isset ( $jsonObj ["spInf49"] ) && isset ( $jsonObj ["spInf50"] ) && isset ( $jsonObj ["spInf51"] ) && isset ( $jsonObj ["spInf52"] ) && isset ( $jsonObj ["spInf53"] ) && isset ( $jsonObj ["spInf54"] ) && isset ( $jsonObj ["spInf55"] ) && isset ( $jsonObj ["spInf56"] ) && isset ( $jsonObj ["spInf57"] ) && isset ( $jsonObj ["spInf58"] ) && isset ( $jsonObj ["spInf59"] ) && isset ( $jsonObj ["spInf60"] ) && isset ( $jsonObj ["spInf61"] ) && isset ( $jsonObj ["spInf62"] )) {
	if (! empty ( $jsonObj ["spInf1"] ) && ! empty ( $jsonObj ["spInf2"] ) && ! empty ( $jsonObj ["spInf3"] )) {
		require_once dirname(__DIR__, 1) . '/config.php';
		$database = mysqli_connect ( DB_SERVER, DB_USER, DB_PASSWORD, DB_DATABASE );
		mysqli_set_charset ( $database, 'utf8' );
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
			$query .= " spInf29=" . $jsonObj ["spInf29"] . ", ";
			$query .= " spInf30=" . $jsonObj ["spInf30"] . ", ";
			$query .= " spInf31=" . $jsonObj ["spInf31"] . ", ";
			$query .= " spInf32=" . $jsonObj ["spInf32"] . ", ";
			$query .= " spInf33=" . $jsonObj ["spInf33"] . ", ";
			$query .= " spInf34=" . $jsonObj ["spInf34"] . ", ";
			$query .= " spInf35=" . $jsonObj ["spInf35"] . ", ";
			$query .= " spInf36=" . $jsonObj ["spInf36"] . ", ";
			$query .= " spInf37=" . $jsonObj ["spInf37"] . ", ";
			$query .= " spInf38=" . $jsonObj ["spInf38"] . ", ";
			$query .= " spInf39='" . $jsonObj ["spInf39"] . "',";
			$query .= " spInf40='" . $jsonObj ["spInf40"] . "',";
			$query .= " spInf41='" . $jsonObj ["spInf41"] . "',";
			$query .= " spInf42=" . $jsonObj ["spInf42"] . ", ";
			$query .= " spInf43=" . $jsonObj ["spInf43"] . ", ";
			$query .= " spInf44=" . $jsonObj ["spInf44"] . ", ";
			$query .= " spInf45=" . $jsonObj ["spInf45"] . ", ";
			$query .= " spInf46=" . $jsonObj ["spInf46"] . ", ";
			$query .= " spInf47='" . $jsonObj ["spInf47"] . "',";
            $query .= " spInf48=" . $jsonObj ["spInf48"] . ", ";
            $query .= " spInf49=" . $jsonObj ["spInf49"] . ", ";
            $query .= " spInf50=" . $jsonObj ["spInf50"] . ", ";
            $query .= " spInf51=" . $jsonObj ["spInf51"] . ", ";
            $query .= " spInf52='" . $jsonObj ["spInf52"] . "',";
			$query .= " spInf53='" . $jsonObj ["spInf53"] . "',";
			$query .= " spInf54=" . $jsonObj ["spInf54"] . ", ";
			$query .= " spInf55=" . $jsonObj ["spInf55"] . ", ";
			$query .= " spInf56=" . $jsonObj ["spInf56"] . ", ";
			$query .= " spInf57=" . $jsonObj ["spInf57"] . ", ";
			$query .= " spInf58=" . $jsonObj ["spInf58"] . ", ";
			$query .= " spInf59=" . $jsonObj ["spInf59"] . ", ";
			$query .= " spInf60=" . $jsonObj ["spInf60"] . ", ";
			$query .= " spInf61=" . $jsonObj ["spInf61"] . ", ";
			
			$query .= " spInf62=" . $jsonObj ["spInf62"] . ", ";
			$query .= " spInf63='" . $jsonObj ["spInf63"] . "',";
			$query .= " spInf64=" . $jsonObj ["spInf64"] . ", ";
			$query .= " spInf65=" . $jsonObj ["spInf65"] . ", ";
			$query .= " spInf66=" . $jsonObj ["spInf66"] . ", ";
			$query .= " spInf67=" . $jsonObj ["spInf67"] . ", ";
			$query .= " spInf68=" . $jsonObj ["spInf68"] . ", ";
			$query .= " spInf69=" . $jsonObj ["spInf69"] . ", ";
			$query .= " spInf70=" . $jsonObj ["spInf70"] . ", ";
			$query .= " spInf71=" . $jsonObj ["spInf71"] . ", ";
			$query .= " spInf72=" . $jsonObj ["spInf72"] . ", ";
			$query .= " spInf73=" . $jsonObj ["spInf73"] . ", ";
			$query .= " spInf74=" . $jsonObj ["spInf74"] . ", ";
			$query .= " spInf75=" . $jsonObj ["spInf75"];
			
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