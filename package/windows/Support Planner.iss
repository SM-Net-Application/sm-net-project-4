;This file will be executed next to the application bundle image
;I.e. current directory will contain folder Support Planner with application files
[Setup]
AppId={{fxApplication}}
AppName=Support Planner
AppVersion=1.0 (beta)
AppVerName=Support Planner 1.0 (beta)
AppPublisher=SM-Net
AppComments=sm-net-project-4
AppCopyright=Copyright (C) 2019
;AppPublisherURL=http://java.com/
;AppSupportURL=http://java.com/
;AppUpdatesURL=http://java.com/
DefaultDirName={localappdata}\Support Planner
DisableStartupPrompt=Yes
DisableDirPage=No
DisableProgramGroupPage=Yes
DisableReadyPage=Yes
DisableFinishedPage=Yes
DisableWelcomePage=Yes
DefaultGroupName=SM-Net
;Optional License
LicenseFile=
;WinXP or above
MinVersion=0,5.1 
OutputBaseFilename=Support Planner-1.0 (beta)
Compression=lzma
SolidCompression=yes
PrivilegesRequired=lowest
SetupIconFile=Support Planner\Support Planner.ico
UninstallDisplayIcon={app}\Support Planner.ico
UninstallDisplayName=Support Planner
WizardImageStretch=No
WizardSmallImageFile=Support Planner-setup-icon.bmp   
ArchitecturesInstallIn64BitMode=x64


[Languages]
Name: "english"; MessagesFile: "compiler:Default.isl"

[Files]
Source: "Support Planner\Support Planner.exe"; DestDir: "{app}"; Flags: ignoreversion
Source: "Support Planner\*"; DestDir: "{app}"; Flags: ignoreversion recursesubdirs createallsubdirs

[Icons]
Name: "{group}\Support Planner"; Filename: "{app}\Support Planner.exe"; IconFilename: "{app}\Support Planner.ico"; Check: returnTrue()
Name: "{commondesktop}\Support Planner"; Filename: "{app}\Support Planner.exe";  IconFilename: "{app}\Support Planner.ico"; Check: returnFalse()


[Run]
Filename: "{app}\Support Planner.exe"; Parameters: "-Xappcds:generatecache"; Check: returnFalse()
Filename: "{app}\Support Planner.exe"; Description: "{cm:LaunchProgram,Support Planner}"; Flags: nowait postinstall skipifsilent; Check: returnTrue()
Filename: "{app}\Support Planner.exe"; Parameters: "-install -svcName ""Support Planner"" -svcDesc ""Support Planner"" -mainExe ""Support Planner.exe""  "; Check: returnFalse()

[UninstallRun]
Filename: "{app}\Support Planner.exe "; Parameters: "-uninstall -svcName Support Planner -stopOnUninstall"; Check: returnFalse()

[Code]
function returnTrue(): Boolean;
begin
  Result := True;
end;

function returnFalse(): Boolean;
begin
  Result := False;
end;

function InitializeSetup(): Boolean;
begin
// Possible future improvements:
//   if version less or same => just launch app
//   if upgrade => check if same app is running and wait for it to exit
//   Add pack200/unpack200 support? 
  Result := True;
end;  
