-- MSSQL
USE [sarangbang]
GO

SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[Address](
    [AddressSeq] [int] IDENTITY(1,1) NOT NULL,
    [SiCode] [nvarchar] (5) NOT NULL,
    [DongCode] [int] NOT NULL DEFAULT '0',
    [FloorCode] [int] NOT NULL DEFAULT '0',
    [RoomCode] [int] NOT NULL DEFAULT '0',
    [SubRoomCode] [int] NOT NULL,
    [DongName] [nvarchar] (50) NOT NULL,
    [FloorName] [nvarchar] (50) NOT NULL,
    [RoomName] [nvarchar] (50) NOT NULL,
    [SubRoomName] [nvarchar] (10) NOT NULL,
    [IsUnderground] [nvarchar] (1) NOT NULL,
    [BuildNumber] [nvarchar] (25) NOT NULL,
    [RawDongCode] [nvarchar] (10) NOT NULL,
    [RoadName] [nvarchar] (12) NOT NULL,
    [IsRoadUnderground] [nvarchar] (1) NOT NULL,
    [BuildMajorNumber] [int] NOT NULL,
    [BuildMinor] [int] NOT NULL,
    [CauseCode] [nvarchar] (2) NULL,
    CONSTRAINT [PK_AddressSeq] PRIMARY KEY CLUSTERED
(
[AddressSeq] ASC
) WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
    ) ON [PRIMARY]
    GO

----
    USE [sarangbang]
    GO

    SET ANSI_NULLS ON
    GO

    SET QUOTED_IDENTIFIER ON
    GO

CREATE TABLE [dbo].[Zipcode](
    [ZipcodeSeq] [int] IDENTITY(1,1) NOT NULL,
    [Zipcode] [nvarchar] (5) NOT NULL,
    [SiDoHan] [nvarchar] (20) NOT NULL,
    [SiDoEng] [nvarchar] (40) NOT NULL,
    [SigunguHan] [nvarchar] (20) NOT NULL,
    [SigunguEng] [nvarchar] (40) NOT NULL,
    [EupMyunHan] [nvarchar] (20) NOT NULL,
    [EupMyunEng] [nvarchar] (40) NOT NULL,
    [RoadNameCode] [nvarchar] (12) NOT NULL,
    [RoadNameHan] [nvarchar] (80) NOT NULL,
    [RoadNameEng] [nvarchar] (80) NOT NULL,
    [IsUnderground] [nvarchar] (1) NOT NULL,
    [BuildingMajorNumber] [int] NOT NULL,
    [BuildingMinorNumber] [int] NOT NULL,
    [BuildingNumber] [nvarchar] (25) NOT NULL,
    [DeliveryLocationName] [nvarchar] (40) NULL,
    [SigunguBuildingName] [nvarchar] (200) NULL,
    [RawDongCode] [nvarchar] (10) NOT NULL,
    [RawDongName] [nvarchar] (20) NOT NULL,
    [LeeName] [nvarchar] (20) NOT NULL,
    [AdministrationDongName] [nvarchar] (40) NOT NULL,
    [IsMountain] [nvarchar] (1) NOT NULL,
    [GiMajorNumber] [int] NOT NULL,
    [EupMyunCode] [nvarchar] (2) NOT NULL,
    [GiMinorNumber] [int] NOT NULL,
    [GuZipcode] [nvarchar] (6) NULL,
    [ZipcodeIndex] [nvarchar] (3) NULL,
    CONSTRAINT [PK_ZipcodeSeq] PRIMARY KEY CLUSTERED
(
[ZipcodeSeq] ASC
) WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
    ) ON [PRIMARY]
    GO

alter table addr_Busan add ZeepCodeSeq int identity(1,1) not null;

alter table addr_Busan add ZeepCodeSeq int  IDENTITY (1,1) NOT NULL;
alter table addr_Chungcheongbukdo add ZeepCodeSeq int  IDENTITY (1,1) NOT NULL;
alter table addr_Chungcheongnamdo add ZeepCodeSeq int  IDENTITY (1,1) NOT NULL;
alter table addr_Daegu add ZeepCodeSeq int  IDENTITY (1,1) NOT NULL;
alter table addr_Daejeon add ZeepCodeSeq int  IDENTITY (1,1) NOT NULL;
alter table addr_Gangwondo add ZeepCodeSeq int  IDENTITY (1,1) NOT NULL;
alter table addr_Gwangju add ZeepCodeSeq int  IDENTITY (1,1) NOT NULL;
alter table addr_Gyeonggido add ZeepCodeSeq int  IDENTITY (1,1) NOT NULL;
alter table addr_Gyeongsangbukdo add ZeepCodeSeq int  IDENTITY (1,1) NOT NULL;
alter table addr_Gyeongsangnamdo add ZeepCodeSeq int  IDENTITY (1,1) NOT NULL;
alter table addr_Incheon add ZeepCodeSeq int  IDENTITY (1,1) NOT NULL;
alter table addr_Jeollabukdo add ZeepCodeSeq int  IDENTITY (1,1) NOT NULL;
alter table addr_Jeollanamdo add ZeepCodeSeq int  IDENTITY (1,1) NOT NULL;
alter table addr_Sejongsi add ZeepCodeSeq int  IDENTITY (1,1) NOT NULL;
alter table addr_Seoul add ZeepCodeSeq int  IDENTITY (1,1) NOT NULL;
alter table addr_Ulsan add ZeepCodeSeq int  IDENTITY (1,1) NOT NULL;


-- 인덱스 만들기 (예제)
-- create clustered index Zipcode on dbo.addr_Busan (zipcode asc); (중복 허용하여 만들기)
-- create clustered index unique Zipcode on dbo.addr_Busan (zipcode asc); (중복없이 만들기)
-- create index Zipcode on dbo.addr_Busan (zipcode asc);  (cluster 없이 만들기)

-- 인덱스 삭제 (예제)
-- drop index Zipcode on dbo.addr_Busan;

-- Zipcode|SiDoHan|SiDoEng|SigunguHan|SigunguEng|EupMyunHan|EupMyunEng|RoadNameCode|RoadNameHan|RoadNameEng|IsUnderground|BuildingMajorNumber|BuildingMinorNumber|BuildingNumber|DeliveryLocationName|SigunguBuildingName|RawDongCode|RawDongName|LeeName|AdministrationDongName|IsMountain|GiMajorNumber|EupMyunCode|GiMinorNumber|GuZipcode|ZipcodeIndex

-- MYSQL (or Mariadb)