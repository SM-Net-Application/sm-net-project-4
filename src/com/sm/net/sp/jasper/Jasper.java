package com.sm.net.sp.jasper;

import java.io.File;

import com.sm.net.file.Extensions;
import com.sm.net.path.PathBuilder;
import com.sm.net.project.Project;

public class Jasper {

	public static final String LAYOUTS = "layouts";

	public static final String TYPE_SM_NET = "sm-net";
	public static final String TYPE_CUSTOM = "custom";

	public static final String LAYOUT_USERS = "sm-net-users";
	public static final String LAYOUT_MEETINGS = "sm-net-meetings";
	public static final String LAYOUT_NATURALDISASTER = "sm-net-naturaldisaster";
	public static final String LAYOUT_MONITOR = "sm-net-monitor";

	public static final String FILE_USERS_LOGO = "iconSP";
	public static final String FILE_USERS_USERS = "spUsers";

	public static final String FILE_MEETINGS_MINISTRY_PART_ROW = "spMeetingMinistryPartRow";
	public static final String FILE_MEETINGS_CHRISTIAN_PART_ROW = "spMeetingChristiansPartRow";
	public static final String FILE_MEETINGS_WEEK = "spMeetingWeek";
	public static final String FILE_MEETINGS_PROGRAMM = "spMeetingProgramm";
	public static final String FILE_NATURAL_DISASTER = "spNaturalDisaster";
	public static final String FILE_NATURAL_DISASTER_FAMILY = "spNaturalDisasterFamilyRow";
	public static final String FILE_NATURAL_DISASTER_MEMBER = "spNaturalDisasterMemberRow";
	public static final String FILE_MONITOR = "spMonitor";

	public static class Layouts {

		public static final File SM_NET_USERS_LOGO = getResource(TYPE_SM_NET, LAYOUT_USERS, FILE_USERS_LOGO,
				Extensions.IMAGE_PNG);

		public static final File SM_NET_USERS_USERS = getLayout(TYPE_SM_NET, LAYOUT_USERS, FILE_USERS_USERS);

		public static final File SM_NET_MEETINGS_MINISTRY_PART_ROW = getLayout(TYPE_SM_NET, LAYOUT_MEETINGS,
				FILE_MEETINGS_MINISTRY_PART_ROW);

		public static final File SM_NET_MEETINGS_CHRISTIANS_PART_ROW = getLayout(TYPE_SM_NET, LAYOUT_MEETINGS,
				FILE_MEETINGS_CHRISTIAN_PART_ROW);

		public static final File SM_NET_MEETINGS_WEEK = getLayout(TYPE_SM_NET, LAYOUT_MEETINGS, FILE_MEETINGS_WEEK);

		public static final File SM_NET_MEETINGS_PROGRAMM = getLayout(TYPE_SM_NET, LAYOUT_MEETINGS,
				FILE_MEETINGS_PROGRAMM);

		public static final File SM_NET_NATURAL_DISASTER = getLayout(TYPE_SM_NET, LAYOUT_NATURALDISASTER,
				FILE_NATURAL_DISASTER);
		public static final File SM_NET_NATURAL_DISASTER_FAMILY = getLayout(TYPE_SM_NET, LAYOUT_NATURALDISASTER,
				FILE_NATURAL_DISASTER_FAMILY);
		public static final File SM_NET_NATURAL_DISASTER_MEMBER = getLayout(TYPE_SM_NET, LAYOUT_NATURALDISASTER,
				FILE_NATURAL_DISASTER_MEMBER);

		public static final File SM_NET_MONITOR = getLayout(TYPE_SM_NET, LAYOUT_MONITOR, FILE_MONITOR);

		public static File getLayout(String layoutType, String layoutName, String layoutFileName) {

			String layout = PathBuilder.concatFolder(Project.currentWorkingDirectory(), LAYOUTS);

			String type = PathBuilder.concatFolder(layout, layoutType);
			String name = PathBuilder.concatFolder(type, layoutName);
			String file = PathBuilder.concatFolder(name, layoutFileName);

			return new File(file.concat(Extensions.JASPER_REPORT));
		}

		public static File getResource(String layoutType, String layoutName, String resourceFileName,
				String resourceExtension) {

			String layout = PathBuilder.concatFolder(Project.currentWorkingDirectory(), LAYOUTS);

			String type = PathBuilder.concatFolder(layout, layoutType);
			String name = PathBuilder.concatFolder(type, layoutName);
			String file = PathBuilder.concatFolder(name, resourceFileName);

			return new File(file.concat(resourceExtension));
		}

	}
}
