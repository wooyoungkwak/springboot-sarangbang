USE [sarangbang]
GO

/****** Object:  Table [dbo].[Person]    Script Date: 2022-03-11 오후 11:44:24 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[Person](
    [PersonSeq] [int] IDENTITY(1,1) NOT NULL,
    [Account] [nvarchar](100) NOT NULL,
    [Password] [nvarchar](100) NOT NULL,
    [Role] [int] NOT NULL,
    [FirstName] [nvarchar](20) NOT NULL,
    [LastName] [nvarchar](20) NULL,
    CONSTRAINT [PK_Person] PRIMARY KEY CLUSTERED
(
[PersonSeq] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
    ) ON [PRIMARY]
    GO


-- -------------------------------------
USE [sarangbang]
GO

/****** Object:  Table [dbo].[BangInfo]    Script Date: 2022-03-11 오후 11:44:40 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[BangInfo](
    [BangInfoSeq] [int] IDENTITY(1,1) NOT NULL,
    [Room] [int] NOT NULL,
    [BathRoom] [int] NOT NULL,
    [Deal] [nvarchar](100) NOT NULL,
    [Floor] [int] NOT NULL,
    [Place] [int] NOT NULL,
    [Ground] [int] NOT NULL,
    [Location] [nvarchar](100) NULL,
    [Heating] [nvarchar](100) NULL,
    [AddressSeq] [int] NULL,
    [Address] [nvarchar](100) NULL,
    [Direction] [nvarchar](100) NULL,
    [price] [int] NOT NULL,
    [RegisterDate] [datetime] NOT NULL,
    [EstateAgencySeq] [int] NULL,
    [IsFavorites] [bit] NOT NULL DEFAULT 0,
    CONSTRAINT [PK_BangInfo] PRIMARY KEY CLUSTERED
(
[BangInfoSeq] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
    ) ON [PRIMARY]
    GO


-- -------------------------------------
USE [sarangbang]
GO

/****** Object:  Table [dbo].[EstateAgency]    Script Date: 2022-03-11 오후 11:44:56 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[EstateAgency](
    [EstateAgencySeq] [int] IDENTITY(1,1) NOT NULL,
    [AddressSeq] [int] NULL,
    [Address] [nvarchar](100) NULL,
    [Name] [nvarchar](100) NOT NULL,
    [Phone] [nvarchar](20) NULL,
    [AgencyName] [nvarchar](100) NULL,
    [AgencyPhone] [nvarchar](20) NULL,
    [IsFavorites] [bit] NOT NULL DEFAULT 0,
    CONSTRAINT [PK_EstateAgency] PRIMARY KEY CLUSTERED
(
[EstateAgencySeq] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
    ) ON [PRIMARY]
    GO



-- -------------------------------------
USE [sarangbang]
GO

/****** Object:  Table [dbo].[StockInfo]    Script Date: 2022-03-11 오후 11:44:56 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[StockInfo](
    [StockInfoSeq] [int] IDENTITY(1,1) NOT NULL,
    [StockType] [int] NOT NULL,
    [Price] [int] NOT NULL,
    [HighPrice] [int] NULL,
    [LowPrice] [int] NULL,
    [regDate] [datetime] NOT NULL,
    [StockCompanySeq] [int] NOT NULL,
    CONSTRAINT [PK_StockInfo] PRIMARY KEY CLUSTERED
(
[StockInfoSeq] ASC
) WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO


-- -------------------------------------
USE [sarangbang]
GO

/****** Object:  Table [dbo].[StockCompany]    Script Date: 2022-03-11 오후 11:44:56 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[StockCompany](
    [StockCompanySeq] [int] IDENTITY(1,1) NOT NULL,
    [Name] [nvarchar](100) NOT NULL,
    [Alias] [nvarchar](100) NOT NULL,
    [Address] [nvarchar](100) NOT NULL,
    [BirthDay] [datetime] NOT NULL,
    CONSTRAINT [PK_StockCompany] PRIMARY KEY CLUSTERED
(
[StockCompanySeq] ASC
) WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO


-- -------------------------------------
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


-- -------------------------------------
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
    [SigunguBuildingName] [nvarchar] (200) NOT NULL,
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

-- 인덱스 만들기 (예제)
-- create clustered index Zipcode on dbo.addr_Busan (zipcode asc); (중복 허용하여 만들기)
-- create clustered index unique Zipcode on dbo.addr_Busan (zipcode asc); (중복없이 만들기)
-- create index Zipcode on dbo.addr_Busan (zipcode asc);  (cluster 없이 만들기)

-- create clustered index Zipcode on dbo.addr_Busan (zipcode asc);
-- create clustered index Zipcode on dbo.addr_Chungcheongbukdo (zipcode asc);
-- create clustered index Zipcode on dbo.addr_Chungcheongnamdo(zipcode asc);
-- create clustered index Zipcode on dbo.addr_Daegu (zipcode asc);
-- create clustered index Zipcode on dbo.addr_Daejeon(zipcode asc);
-- create clustered index Zipcode on dbo.addr_Gangwondo(zipcode asc);
-- create clustered index Zipcode on dbo.addr_Gwangju(zipcode asc);
-- create clustered index Zipcode on dbo.addr_Gyeonggido(zipcode asc);
-- create clustered index Zipcode on dbo.addr_Gyeongsangbukdo(zipcode asc);
-- create clustered index Zipcode on dbo.addr_Gyeongsangnamdo(zipcode asc);
-- create clustered index Zipcode on dbo.addr_Incheon(zipcode asc);
-- create clustered index Zipcode on dbo.addr_Jeollabukdo(zipcode asc);
-- create clustered index Zipcode on dbo.addr_Jeollanamdo(zipcode asc);
-- create clustered index Zipcode on dbo.addr_Sejongsi(zipcode asc);
-- create clustered index Zipcode on dbo.addr_Seoul(zipcode asc);
-- create clustered index Zipcode on dbo.addr_Ulsan(zipcode asc);

-- 인덱스 삭제 (예제)
-- drop index Zipcode on dbo.addr_Busan;



-- -------------------------------------
USE [sarangbang]
GO

SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[FileInfo](
    [FileInfoSeq] [int] IDENTITY(1,1) NOT NULL,
    [FileType] [int] NOT NULL DEFAULT 0,
    [Description] [nvarchar] (100) NOT NULL,
    [FileName] [nvarchar] (100) NOT NULL,
    [regDt] [datetime] NOT NULL,
    [delDt] [datetime] NULL,
    [delYN] [bit] NOT NULL,
    CONSTRAINT [FileInfoSeq] PRIMARY KEY CLUSTERED
(
[FileInfoSeq] ASC
) WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO


-- --------------------------------------------
-- create procedure --
USE [sarangbang]
GO

/****** Object:  StoredProcedure [dbo].[selectBusan]    Script Date: 2022-03-27 오후 10:08:39 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE procedure [dbo].[selectZipcode]
( @SidoEng nvarchar(50), @zipcode nvarchar(50))
as
begin

IF (@SidoEng = 'Busan') begin select * from addr_Busan where Zipcode = @zipcode; end
ELSE IF (@SidoEng = 'Chungcheongbukdo') begin select * from addr_Chungcheongbukdo where Zipcode = @zipcode; end
ELSE IF (@SidoEng = 'Chungcheongnamdo') begin select * from addr_Chungcheongnamdo where Zipcode = @zipcode; end
ELSE IF (@SidoEng = 'Daegu') begin select * from addr_Daegu where Zipcode = @zipcode; end
ELSE IF (@SidoEng = 'Daejeon') begin select * from addr_Daejeon where Zipcode = @zipcode; end
ELSE IF (@SidoEng = 'Gangwondo') begin select * from addr_Gangwondo where Zipcode = @zipcode; end
ELSE IF (@SidoEng = 'Gwangju') begin select * from addr_Gwangju where Zipcode = @zipcode; end
ELSE IF (@SidoEng = 'Gyeonggido') begin select * from addr_Gyeonggido where Zipcode = @zipcode; end
ELSE IF (@SidoEng = 'Gyeongsangbukdo') begin select * from addr_Gyeongsangbukdo where Zipcode = @zipcode; end
ELSE IF (@SidoEng = 'Gyeongsangnamdo') begin select * from addr_Gyeongsangnamdo where Zipcode = @zipcode; end
ELSE IF (@SidoEng = 'Incheon') begin select * from addr_Incheon where Zipcode = @zipcode; end
ELSE IF (@SidoEng = 'Jeollabukdo') begin select * from addr_Jeollabukdo where Zipcode = @zipcode; end
ELSE IF (@SidoEng = 'Jeollanamdo') begin select * from addr_Jeollanamdo where Zipcode = @zipcode; end
ELSE IF (@SidoEng = 'Sejongsi') begin select * from addr_Sejongsi where Zipcode = @zipcode; end
ELSE IF (@SidoEng = 'Seoul') begin select * from addr_Seoul where Zipcode = @zipcode; end
ELSE IF (@SidoEng = 'Ulsan') begin select * from addr_Ulsan where Zipcode = @zipcode; end

end
GO
