<?php
// Insert Week
if (isset ( $jsonObj ["spInf1"] ) && isset ( $jsonObj ["spInf2"] ) && isset ( $jsonObj ["spInf3"] ) && isset ( $jsonObj ["spInf4"] ) && isset ( $jsonObj ["spInf5"] ) && isset ( $jsonObj ["spInf6"] ) && isset ( $jsonObj ["spInf7"] ) && isset ( $jsonObj ["spInf8"] ) && isset ( $jsonObj ["spInf9"] ) && isset ( $jsonObj ["spInf10"] ) && isset ( $jsonObj ["spInf11"] ) && isset ( $jsonObj ["spInf12"] ) && isset ( $jsonObj ["spInf13"] ) && isset ( $jsonObj ["spInf14"] ) && isset ( $jsonObj ["spInf15"] ) && isset ( $jsonObj ["spInf16"] ) && isset ( $jsonObj ["spInf17"] ) && isset ( $jsonObj ["spInf18"] ) && isset ( $jsonObj ["spInf19"] ) && isset ( $jsonObj ["spInf20"] ) && isset ( $jsonObj ["spInf21"] ) && isset ( $jsonObj ["spInf22"] ) && isset ( $jsonObj ["spInf23"] ) && isset ( $jsonObj ["spInf24"] ) && isset ( $jsonObj ["spInf25"] ) && isset ( $jsonObj ["spInf26"] ) && isset ( $jsonObj ["spInf27"] ) && isset ( $jsonObj ["spInf28"] ) && isset ( $jsonObj ["spInf29"] ) && isset ( $jsonObj ["spInf30"] ) && isset ( $jsonObj ["spInf31"] ) && isset ( $jsonObj ["spInf32"] ) && isset ( $jsonObj ["spInf33"] ) && isset ( $jsonObj ["spInf34"] ) && isset ( $jsonObj ["spInf35"] ) && isset ( $jsonObj ["spInf36"] ) && isset ( $jsonObj ["spInf37"] ) && isset ( $jsonObj ["spInf38"] ) && isset ( $jsonObj ["spInf39"] ) && isset ( $jsonObj ["spInf40"] ) && isset ( $jsonObj ["spInf41"] ) && isset ( $jsonObj ["spInf42"] ) && isset ( $jsonObj ["spInf43"] ) && isset ( $jsonObj ["spInf44"] ) && isset ( $jsonObj ["spInf45"] ) && isset ( $jsonObj ["spInf46"] ) && isset ( $jsonObj ["spInf47"] ) && isset ( $jsonObj ["spInf48"] ) && isset ( $jsonObj ["spInf49"] ) && isset ( $jsonObj ["spInf50"] ) && isset ( $jsonObj ["spInf51"] ) && isset ( $jsonObj ["spInf52"] ) && isset ( $jsonObj ["spInf53"] ) && isset ( $jsonObj ["spInf54"] ) && isset ( $jsonObj ["spInf55"] ) && isset ( $jsonObj ["spInf56"] ) && isset ( $jsonObj ["spInf57"] ) && isset ( $jsonObj ["spInf58"] ) && isset ( $jsonObj ["spInfMP"] ) && isset ( $jsonObj ["spInfCP"] )) {
    
	if (! empty ( $jsonObj ["spInf1"] ) && ! empty ( $jsonObj ["spInf2"] ) && ! empty ( $jsonObj ["spInf5"] ) && ! empty ( $jsonObj ["spInf6"] ) && ! empty ( $jsonObj ["spInf7"] ) && ! empty ( $jsonObj ["spInf8"] ) && ! empty ( $jsonObj ["spInf9"] ) && ! empty ( $jsonObj ["spInf10"] ) && ! empty ( $jsonObj ["spInf12"] ) && ! empty ( $jsonObj ["spInf13"] ) && ! empty ( $jsonObj ["spInf15"] ) && ! empty ( $jsonObj ["spInf16"] ) && ! empty ( $jsonObj ["spInf17"] ) && ! empty ( $jsonObj ["spInf19"] ) && ! empty ( $jsonObj ["spInf20"] ) && ! empty ( $jsonObj ["spInf21"] ) && ! empty ( $jsonObj ["spInf22"] ) && ! empty ( $jsonObj ["spInf24"] ) && ! empty ( $jsonObj ["spInf25"] ) && ! empty ( $jsonObj ["spInf26"] )) {
		require_once dirname(__DIR__, 1) . '/config.php';
		$database = mysqli_connect ( DB_SERVER, DB_USER, DB_PASSWORD, DB_DATABASE );
		mysqli_set_charset ( $database, 'utf8' );
		if (! $database) {
			$response ["status"] = 4;
			$response ["error"] = mysqli_connect_error ();
		} else {
			$query = "INSERT INTO sp_week (spInf1, spInf2, spInf3, spInf4, spInf5,";
			$query .= "spInf6, spInf7, spInf8, spInf9, spInf10, spInf11, spInf12,";
			$query .= "spInf13, spInf14, spInf15, spInf16, spInf17, spInf18, spInf19,";
			$query .= "spInf20, spInf21, spInf22, spInf23, spInf24, spInf25, spInf26, spInf27, spInf28";
            $query .= ",spInf29, spInf30, spInf31, spInf32, spInf33, spInf34, spInf35, spInf36, spInf37";
            $query .= ",spInf38, spInf39, spInf40, spInf41, spInf42, spInf43, spInf44, spInf45, spInf46";
			$query .= ",spInf47, spInf48, spInf49, spInf50, spInf51, spInf52, spInf53";
			$query .= ",spInf54, spInf55, spInf56, spInf57, spInf58";
            $query .= ")";
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
			$query .= ", ";
			$query .= $jsonObj ["spInf28"];
            $query .= ", ";
			$query .= $jsonObj ["spInf29"];
            $query .= ", ";
			$query .= $jsonObj ["spInf30"];
            $query .= ", ";
			$query .= "'" . $jsonObj ["spInf31"] . "'";
            $query .= ", ";
			$query .= "'" . $jsonObj ["spInf32"] . "'";
            $query .= ", ";
			$query .= "'" . $jsonObj ["spInf33"] . "'";
            $query .= ", ";
			$query .= "'" . $jsonObj ["spInf34"] . "'";
            $query .= ", ";
			$query .= "'" . $jsonObj ["spInf35"] . "'";
            $query .= ", ";
			$query .= "'" . $jsonObj ["spInf36"] . "'";
            $query .= ", ";
			$query .= $jsonObj ["spInf37"];
            $query .= ", ";
			$query .= $jsonObj ["spInf38"];
            $query .= ", ";
			$query .= "'" . $jsonObj ["spInf39"] . "'";
            $query .= ", ";
			$query .= $jsonObj ["spInf40"];
            $query .= ", ";
			$query .= $jsonObj ["spInf41"];
            $query .= ", ";
			$query .= "'" . $jsonObj ["spInf42"] . "'";
            $query .= ", ";
			$query .= "'" . $jsonObj ["spInf43"] . "'";
            $query .= ", ";
			$query .= $jsonObj ["spInf44"];
            $query .= ", ";
			$query .= $jsonObj ["spInf45"];
            $query .= ", ";
			$query .= $jsonObj ["spInf46"];
            $query .= ", ";
			$query .= $jsonObj ["spInf47"];
            $query .= ", ";
			$query .= $jsonObj ["spInf48"];
            $query .= ", ";
			$query .= $jsonObj ["spInf49"];
            $query .= ", ";
			$query .= "'" . $jsonObj ["spInf50"] . "'";
            $query .= ", ";
			$query .= $jsonObj ["spInf51"];
            $query .= ", ";
			$query .= "'" . $jsonObj ["spInf52"] . "'";
            $query .= ", ";
			$query .= $jsonObj ["spInf53"];
			$query .= ", ";
			$query .= "'" . $jsonObj ["spInf54"] . "'";
			$query .= ", ";
			$query .= "'" . $jsonObj ["spInf55"] . "'";
			$query .= ", ";
			$query .= $jsonObj ["spInf56"];
			$query .= ", ";
			$query .= $jsonObj ["spInf57"];
			$query .= ", ";
			$query .= $jsonObj ["spInf58"];
			$query .= ")";
			
			if ($database->query ( $query ) === TRUE) {
				$response ["status"] = 0;
				
				// Ministry Parts
				$query = "DELETE FROM sp_week_min";
				$query .= " WHERE spInf1 = '" . $jsonObj ["spInf1"] . "'";
				
				if (! ($database->query ( $query ) === TRUE)) {
					$response ["error"] .= "<Non sono riuscito a cancellare le parti Ministero>";
				} else {
					
					if (! empty ( $jsonObj ["spInfMP"] )) {
						
						$query = "INSERT INTO sp_week_min (spInf1, spInf2, spInf3, spInf4, spInf5, spInf6, spInf7, spInf8, spInf9, spInf10)";
						$query .= " VALUES " . $jsonObj ["spInfMP"];
						
						if (! ($database->query ( $query ) === TRUE)) {
							$response ["error"] .= "<Non sono riuscito ad inserire le parti Ministero>";
						}
					}
				}
				
				// Christians Parts
				$query = "DELETE FROM sp_week_cr";
				$query .= " WHERE spInf1 = '" . $jsonObj ["spInf1"] . "'";
				
				if (! ($database->query ( $query ) === TRUE)) {
					$response ["error"] .= "<Non sono riuscito a cancellare le parti Vita cristiana>";
				} else {
					
					if (! empty ( $jsonObj ["spInfCP"] )) {
						
						$query = "INSERT INTO sp_week_cr (spInf1, spInf2, spInf3, spInf4, spInf5, spInf6)";
						$query .= " VALUES " . $jsonObj ["spInfCP"];
						
						if (! ($database->query ( $query ) === TRUE)) {
							$response ["error"] .= "<Non sono riuscito ad inserire le parti Vita cristiana>";
						}
					}
				}
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