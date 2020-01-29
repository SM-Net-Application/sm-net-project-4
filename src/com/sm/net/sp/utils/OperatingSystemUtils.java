package com.sm.net.sp.utils;

public class OperatingSystemUtils {

	public static EnumOperatingSystem getOperatingSystem() {

		final String osName = System.getProperty("os.name").toLowerCase();

		System.out.println("osName : " + osName);

		if (!osName.isEmpty()) {

			if (osName.contains("windows"))
				return EnumOperatingSystem.WINDOWS;

			if (osName.contains("linux"))
				return EnumOperatingSystem.LINUX;

			if (osName.contains("mac"))
				return EnumOperatingSystem.MAC;

		}

		return null;
	}

	public static EnumOperatingSystemArchitecture getOperatingSystemArchitecture() {

		final String osArch = System.getProperty("os.arch").toLowerCase();

		System.out.println("osArch : " + osArch);
		
		if (!osArch.isEmpty())
			return osArch.contains("86")
					? EnumOperatingSystemArchitecture.BIT32
					: EnumOperatingSystemArchitecture.BIT64;

		return null;
	}
}
