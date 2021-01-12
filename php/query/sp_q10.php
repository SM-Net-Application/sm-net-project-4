<?php
// Insert member
if (isset ( $jsonObj ["spInf1"] ) && isset ( $jsonObj ["spInf2"] ) && isset ( $jsonObj ["spInf3"] ) && isset ( $jsonObj ["spInf4"] ) && isset ( $jsonObj ["spInf5"] ) && isset ( $jsonObj ["spInf6"] ) && isset ( $jsonObj ["spInf7"] ) && isset ( $jsonObj ["spInf8"] ) && isset ( $jsonObj ["spInf9"] ) && isset ( $jsonObj ["spInf10"] ) && isset ( $jsonObj ["spInf11"] ) && isset ( $jsonObj ["spInf12"] ) && isset ( $jsonObj ["spInf13"] ) && isset ( $jsonObj ["spInf14"] ) && isset ( $jsonObj ["spInf15"] ) && isset ( $jsonObj ["spInf16"] ) && isset ( $jsonObj ["spInf17"] ) && isset ( $jsonObj ["spInf18"] ) && isset ( $jsonObj ["spInf19"] ) && isset ( $jsonObj ["spInf20"] ) && isset ( $jsonObj ["spInf21"] ) && isset ( $jsonObj ["spInf22"] ) && isset ( $jsonObj ["spInf23"] ) && isset ( $jsonObj ["spInf24"] ) && isset ( $jsonObj ["spInf25"] ) && isset ( $jsonObj ["spInf26"] ) && isset ( $jsonObj ["spInf27"] ) && isset ( $jsonObj ["spInf28"] ) && isset ( $jsonObj ["spInf29"] ) && isset ( $jsonObj ["spInf30"] ) && isset ( $jsonObj ["spInf31"] ) && isset ( $jsonObj ["spInf32"] ) && isset ( $jsonObj ["spInf33"] ) && isset ( $jsonObj ["spInf34"] ) && isset ( $jsonObj ["spInf35"] ) && isset ( $jsonObj ["spInf36"] ) && isset ( $jsonObj ["spInf37"] ) && isset ( $jsonObj ["spInf38"] ) && isset ( $jsonObj ["spInf39"] ) && isset ( $jsonObj ["spInf40"] ) && isset ( $jsonObj ["spInf41"] ) && isset ( $jsonObj ["spInf42"] ) && isset ( $jsonObj ["spInf43"] ) && isset ( $jsonObj ["spInf44"] ) && isset ( $jsonObj ["spInf45"] ) && isset ( $jsonObj ["spInf46"] ) && isset ( $jsonObj ["spInf47"] ) && isset ( $jsonObj ["spInf48"] ) && isset ( $jsonObj ["spInf49"] ) && isset ( $jsonObj ["spInf50"] ) && isset ( $jsonObj ["spInf51"] ) && isset ( $jsonObj ["spInf52"] ) && isset ( $jsonObj ["spInf53"] ) && isset ( $jsonObj ["spInf54"] ) && isset ( $jsonObj ["spInf55"] ) && isset ( $jsonObj ["spInf56"] ) && isset ( $jsonObj ["spInf57"] ) && isset ( $jsonObj ["spInf58"] ) && isset ( $jsonObj ["spInf59"] ) && isset ( $jsonObj ["spInf60"] ) && isset ( $jsonObj ["spInf61"] ) && isset ( $jsonObj ["spInf62"] )) {
	if (! empty ( $jsonObj ["spInf1"] ) && ! empty ( $jsonObj ["spInf2"] ) && ! empty ( $jsonObj ["spInf3"] )) {
		require_once dirname(__DIR__, 1) . '/config.php';
		$database = mysqli_connect ( DB_SERVER, DB_USER, DB_PASSWORD, DB_DATABASE );
		mysqli_set_charset ( $database, 'utf8' );
		if (! $database) {
			$response ["status"] = 4;
			$response ["error"] = mysqli_connect_error ();
		} else {
			$query = "INSERT INTO sp_members (spInf1, spInf2, spInf3, spInf4, spInf5, spInf6, spInf7, spInf8, spInf9, spInf10, spInf11, spInf12, spInf13, spInf14, spInf15, spInf16, spInf17, spInf18, spInf19, spInf20, spInf21, spInf22, spInf23, spInf24, spInf25, spInf26, spInf27, spInf28, spInf29, spInf30, spInf31, spInf32, spInf33, spInf34, spInf35, spInf36, spInf37, spInf38, spInf39, spInf40, spInf41, spInf42, spInf43, spInf44, spInf45, spInf46, spInf47, spInf48, spInf49, spInf50, spInf51, spInf52, spInf53, spInf54, spInf55, spInf56, spInf57, spInf58, spInf59, spInf60, spInf61, spInf62) VALUES (";
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
			$query .= $jsonObj ["spInf29"] . ",";
			$query .= $jsonObj ["spInf30"] . ",";
			$query .= $jsonObj ["spInf31"] . ",";
			$query .= $jsonObj ["spInf32"] . ",";
			$query .= $jsonObj ["spInf33"] . ",";
			$query .= $jsonObj ["spInf34"] . ",";
			$query .= $jsonObj ["spInf35"] . ",";
			$query .= $jsonObj ["spInf36"] . ",";
			$query .= $jsonObj ["spInf37"] . ",";
			$query .= $jsonObj ["spInf38"] . ",";
			$query .= "'" . $jsonObj ["spInf39"] . "',";
			$query .= "'" . $jsonObj ["spInf40"] . "',";
			$query .= "'" . $jsonObj ["spInf41"] . "',";
			$query .= $jsonObj ["spInf42"] . ",";
			$query .= $jsonObj ["spInf43"] . ",";
			$query .= $jsonObj ["spInf44"] . ",";
			$query .= $jsonObj ["spInf45"] . ",";
			$query .= $jsonObj ["spInf46"] . ",";
			$query .= "'" . $jsonObj ["spInf47"] . "',";
            $query .= $jsonObj ["spInf48"] . ",";
            $query .= $jsonObj ["spInf49"] . ",";
            $query .= $jsonObj ["spInf50"] . ",";
            $query .= $jsonObj ["spInf51"] . ",";
            $query .= "'" . $jsonObj ["spInf52"] . "',";
			$query .= "'" . $jsonObj ["spInf53"] . "',";
			$query .= $jsonObj ["spInf54"] . ",";
			$query .= $jsonObj ["spInf55"] . ",";
			$query .= $jsonObj ["spInf56"] . ",";
			$query .= $jsonObj ["spInf57"] . ",";
			$query .= $jsonObj ["spInf58"] . ",";
			$query .= $jsonObj ["spInf59"] . ",";
			$query .= $jsonObj ["spInf60"] . ",";
			$query .= $jsonObj ["spInf61"] . ",";
			$query .= $jsonObj ["spInf62"];
            
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