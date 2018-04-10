create table BUCKZY_CustomUser (
	customUserId LONG not null primary key,
	userId LONG,
	partyId LONG,
	partyUserId LONG,
	mobileNo VARCHAR(75) null,
	mobCountryCode VARCHAR(75) null,
	deviceInfo VARCHAR(500) null,
	documentVerified BOOLEAN,
	accountCompleted BOOLEAN,
	profileComplete BOOLEAN,
	socialLogin BOOLEAN,
	restPass VARCHAR(100) null,
	createDate DATE null,
	createdBy LONG,
	modifiedDate DATE null,
	modifiedBy LONG
);