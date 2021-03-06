USE [master]
GO
/****** Object:  Database [SimpleBlog]    Script Date: 1/19/2020 1:17:51 AM ******/
CREATE DATABASE [SimpleBlog]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'SimpleBlog', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.MSSQLSERVER\MSSQL\DATA\SimpleBlog.mdf' , SIZE = 4096KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'SimpleBlog_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.MSSQLSERVER\MSSQL\DATA\SimpleBlog_log.ldf' , SIZE = 1024KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO
ALTER DATABASE [SimpleBlog] SET COMPATIBILITY_LEVEL = 120
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [SimpleBlog].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [SimpleBlog] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [SimpleBlog] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [SimpleBlog] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [SimpleBlog] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [SimpleBlog] SET ARITHABORT OFF 
GO
ALTER DATABASE [SimpleBlog] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [SimpleBlog] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [SimpleBlog] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [SimpleBlog] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [SimpleBlog] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [SimpleBlog] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [SimpleBlog] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [SimpleBlog] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [SimpleBlog] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [SimpleBlog] SET  DISABLE_BROKER 
GO
ALTER DATABASE [SimpleBlog] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [SimpleBlog] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [SimpleBlog] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [SimpleBlog] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [SimpleBlog] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [SimpleBlog] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [SimpleBlog] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [SimpleBlog] SET RECOVERY FULL 
GO
ALTER DATABASE [SimpleBlog] SET  MULTI_USER 
GO
ALTER DATABASE [SimpleBlog] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [SimpleBlog] SET DB_CHAINING OFF 
GO
ALTER DATABASE [SimpleBlog] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [SimpleBlog] SET TARGET_RECOVERY_TIME = 0 SECONDS 
GO
ALTER DATABASE [SimpleBlog] SET DELAYED_DURABILITY = DISABLED 
GO
EXEC sys.sp_db_vardecimal_storage_format N'SimpleBlog', N'ON'
GO
USE [SimpleBlog]
GO
/****** Object:  Table [dbo].[Article]    Script Date: 1/19/2020 1:17:52 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Article](
	[Title] [varchar](50) NOT NULL,
	[ShortDescription] [varchar](255) NULL,
	[Content] [text] NULL,
	[Author] [varchar](50) NULL,
	[PostingDate] [datetime] NULL,
	[StatusArticle] [varchar](20) NULL,
	[OldStatus] [varchar](20) NULL,
 CONSTRAINT [PK_Article] PRIMARY KEY CLUSTERED 
(
	[Title] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Comments]    Script Date: 1/19/2020 1:17:53 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Comments](
	[IDComment] [int] NOT NULL,
	[Name] [varchar](50) NULL,
	[Comment] [text] NULL,
	[Title] [varchar](50) NULL,
 CONSTRAINT [PK_Comments] PRIMARY KEY CLUSTERED 
(
	[IDComment] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Registration]    Script Date: 1/19/2020 1:17:53 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Registration](
	[Email] [varchar](50) NOT NULL,
	[Name] [varchar](50) NOT NULL,
	[Password] [varchar](max) NOT NULL,
	[Role] [varchar](20) NULL,
	[Status] [varchar](20) NULL,
 CONSTRAINT [PK_Registration] PRIMARY KEY CLUSTERED 
(
	[Email] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
INSERT [dbo].[Article] ([Title], [ShortDescription], [Content], [Author], [PostingDate], [StatusArticle], [OldStatus]) VALUES (N'An mang tren song Nile', N'truyen trinh tham', N'aokxcv wer', N'LittleMozartHVer@gmail.com', CAST(N'2018-11-26 12:05:30.000' AS DateTime), N'Active', N'')
INSERT [dbo].[Article] ([Title], [ShortDescription], [Content], [Author], [PostingDate], [StatusArticle], [OldStatus]) VALUES (N'Bo Gia', N'xa hoi den', N'asdfghjklqwertyuiopzxcv bnm', N'LittleMozartHVer@gmail.com', CAST(N'1999-04-23 03:10:12.000' AS DateTime), N'Deleted', N'Active')
INSERT [dbo].[Article] ([Title], [ShortDescription], [Content], [Author], [PostingDate], [StatusArticle], [OldStatus]) VALUES (N'Bong da', N'bong da', N'cau chuyen bong da', N'quynh@gmail.com', CAST(N'2020-01-18 21:12:53.960' AS DateTime), N'Active', N'')
INSERT [dbo].[Article] ([Title], [ShortDescription], [Content], [Author], [PostingDate], [StatusArticle], [OldStatus]) VALUES (N'Code dao', N'lap trinh', N'aopou zxcvx', N'hoang_25@gmail.com.vn', CAST(N'2019-10-10 03:10:12.000' AS DateTime), N'New', N'')
INSERT [dbo].[Article] ([Title], [ShortDescription], [Content], [Author], [PostingDate], [StatusArticle], [OldStatus]) VALUES (N'Code learn', N'how to code', N'cococodaodaodao', N'hoang_25@gmail.com.vn', CAST(N'2020-01-17 22:00:58.777' AS DateTime), N'Active', N'')
INSERT [dbo].[Article] ([Title], [ShortDescription], [Content], [Author], [PostingDate], [StatusArticle], [OldStatus]) VALUES (N'Conan', N'trinh tham', N'zxcvsdfs', N'LittleMozartHVer@gmail.com', CAST(N'2008-08-25 06:16:23.000' AS DateTime), N'Active', N'')
INSERT [dbo].[Article] ([Title], [ShortDescription], [Content], [Author], [PostingDate], [StatusArticle], [OldStatus]) VALUES (N'Dac nhan tam', N'sach tu giup ban than', N'adfdk dsfkj', N'quynh@gmail.com', CAST(N'2015-07-19 17:08:09.000' AS DateTime), N'Active', N'')
INSERT [dbo].[Article] ([Title], [ShortDescription], [Content], [Author], [PostingDate], [StatusArticle], [OldStatus]) VALUES (N'Dekiru', N'hoc tieng nhat', N'asdfewe', N'hoang_25@gmail.com.vn', CAST(N'2019-03-09 19:00:09.000' AS DateTime), N'Active', N'')
INSERT [dbo].[Article] ([Title], [ShortDescription], [Content], [Author], [PostingDate], [StatusArticle], [OldStatus]) VALUES (N'Doi thay doi khi chung ta thay doi', N'cach ung xu thuc te', N'alkj dfj', N'quynh@gmail.com', CAST(N'2020-01-03 17:08:09.000' AS DateTime), N'Active', N'')
INSERT [dbo].[Article] ([Title], [ShortDescription], [Content], [Author], [PostingDate], [StatusArticle], [OldStatus]) VALUES (N'Doi thay doi khi ta thay doi', N'day doi', N'dodododododdo', N'LittleMozartHVer@gmail.com', CAST(N'2020-01-12 11:56:19.000' AS DateTime), N'Active', N'')
INSERT [dbo].[Article] ([Title], [ShortDescription], [Content], [Author], [PostingDate], [StatusArticle], [OldStatus]) VALUES (N'Doraemon', N'truyen tranh', N'zxc vbn mnba', N'quynh@gmail.com', CAST(N'2018-09-30 12:21:28.000' AS DateTime), N'New', N'')
INSERT [dbo].[Article] ([Title], [ShortDescription], [Content], [Author], [PostingDate], [StatusArticle], [OldStatus]) VALUES (N'Dragon Balls', N'Truyen tranh nhat', N'asdfxcv', N'LittleMozartHVer@gmail.com', CAST(N'2016-02-02 20:37:09.000' AS DateTime), N'Deleted', N'Active')
INSERT [dbo].[Article] ([Title], [ShortDescription], [Content], [Author], [PostingDate], [StatusArticle], [OldStatus]) VALUES (N'Harry Potter', N'khoa hoc vien tuong', N'aswed cxvkm dfs', N'quynh@gmail.com', CAST(N'2002-02-02 19:00:09.000' AS DateTime), N'Deleted', N'New')
INSERT [dbo].[Article] ([Title], [ShortDescription], [Content], [Author], [PostingDate], [StatusArticle], [OldStatus]) VALUES (N'Mat biec', N'truyen tinh cam', N'asdhl klml', N'hoang_25@gmail.com.vn', CAST(N'2013-06-06 19:00:09.000' AS DateTime), N'Deleted', N'Active')
INSERT [dbo].[Article] ([Title], [ShortDescription], [Content], [Author], [PostingDate], [StatusArticle], [OldStatus]) VALUES (N'Mat ma Da Vinci', N'suy luan, hu cau huyn bi', N'aljxis sdf', N'quynh@gmail.com', CAST(N'2003-03-14 06:16:23.000' AS DateTime), N'Active', N'')
INSERT [dbo].[Article] ([Title], [ShortDescription], [Content], [Author], [PostingDate], [StatusArticle], [OldStatus]) VALUES (N'Naruto', N'hoat hinh nhat ban', N'hahahahahahahahaha hihihiihihihi', N'quynh@gmail.com', CAST(N'2020-01-12 03:10:12.000' AS DateTime), N'Deleted', N'New')
INSERT [dbo].[Article] ([Title], [ShortDescription], [Content], [Author], [PostingDate], [StatusArticle], [OldStatus]) VALUES (N'Nha gia kim', N'tu truyen', N'asdfi owre', N'hoang_25@gmail.com.vn', CAST(N'2019-01-18 08:13:54.000' AS DateTime), N'Deleted', N'Active')
INSERT [dbo].[Article] ([Title], [ShortDescription], [Content], [Author], [PostingDate], [StatusArticle], [OldStatus]) VALUES (N'Nick Vujicic', N'nghi luc cuoc song', N'asfoixcvy', N'quynh@gmail.com', CAST(N'2013-07-21 12:05:30.000' AS DateTime), N'New', N'')
INSERT [dbo].[Article] ([Title], [ShortDescription], [Content], [Author], [PostingDate], [StatusArticle], [OldStatus]) VALUES (N'Phia tay bien gioi phia nam mat troi', N'truyen tinh cam', N'asd kjxciv woire', N'LittleMozartHVer@gmail.com', CAST(N'2019-12-20 07:45:56.000' AS DateTime), N'Deleted', N'Active')
INSERT [dbo].[Article] ([Title], [ShortDescription], [Content], [Author], [PostingDate], [StatusArticle], [OldStatus]) VALUES (N'Rich dad, Poor dad', N'sach lam giau', N'adfg ijo ', N'quynh@gmail.com', CAST(N'2018-11-20 14:07:36.000' AS DateTime), N'Active', N'')
INSERT [dbo].[Article] ([Title], [ShortDescription], [Content], [Author], [PostingDate], [StatusArticle], [OldStatus]) VALUES (N'Secrets of the Millionaire Mind', N'hoc lam giau', N'asdf jkxiv wer', N'hoang_25@gmail.com.vn', CAST(N'2011-01-13 11:56:19.000' AS DateTime), N'Deleted', N'Active')
INSERT [dbo].[Article] ([Title], [ShortDescription], [Content], [Author], [PostingDate], [StatusArticle], [OldStatus]) VALUES (N'Sherlock Holmes', N'trinh tham', N'asdf lcxb', N'quynh@gmail.com', CAST(N'2005-12-15 14:07:36.000' AS DateTime), N'New', N'')
INSERT [dbo].[Article] ([Title], [ShortDescription], [Content], [Author], [PostingDate], [StatusArticle], [OldStatus]) VALUES (N'Suy nghi va lam giau', N'hoc lam giau', N'aoroyt', N'hoang_25@gmail.com.vn', CAST(N'2011-01-23 12:05:30.000' AS DateTime), N'Active', N'')
INSERT [dbo].[Article] ([Title], [ShortDescription], [Content], [Author], [PostingDate], [StatusArticle], [OldStatus]) VALUES (N'Tat den', N'truyen thoi chien tranh', N'akj dssk', N'LittleMozartHVer@gmail.com', CAST(N'2012-05-05 06:16:23.000' AS DateTime), N'Active', N'')
INSERT [dbo].[Article] ([Title], [ShortDescription], [Content], [Author], [PostingDate], [StatusArticle], [OldStatus]) VALUES (N'The conjuring', N'truyen kinh di', N'mamamamamamama', N'hoang_25@gmail.com.vn', CAST(N'2020-01-12 20:37:09.000' AS DateTime), N'Active', N'')
INSERT [dbo].[Article] ([Title], [ShortDescription], [Content], [Author], [PostingDate], [StatusArticle], [OldStatus]) VALUES (N'Thep da toi the day', N'pham chat con nguoi', N'adbhoi', N'hoang_25@gmail.com.vn', CAST(N'2019-03-19 12:05:30.000' AS DateTime), N'Active', N'')
INSERT [dbo].[Article] ([Title], [ShortDescription], [Content], [Author], [PostingDate], [StatusArticle], [OldStatus]) VALUES (N'Toan cao cap', N'phuong phap hoc toan', N'totototototototot tototototot', N'LittleMozartHVer@gmail.com', CAST(N'2020-01-12 14:07:36.000' AS DateTime), N'Active', N'')
INSERT [dbo].[Article] ([Title], [ShortDescription], [Content], [Author], [PostingDate], [StatusArticle], [OldStatus]) VALUES (N'Toi tai gioi, ban cung the', N'chia se phuong phap hoc tap', N'akjdfg osijdfjk', N'hoang_25@gmail.com.vn', CAST(N'2018-12-02 17:08:09.000' AS DateTime), N'Active', N'')
INSERT [dbo].[Article] ([Title], [ShortDescription], [Content], [Author], [PostingDate], [StatusArticle], [OldStatus]) VALUES (N'Tony buoi sang', N'chia se van hoa', N'asdfd khskf', N'LittleMozartHVer@gmail.com', CAST(N'2014-07-15 06:16:23.000' AS DateTime), N'Deleted', N'Active')
INSERT [dbo].[Article] ([Title], [ShortDescription], [Content], [Author], [PostingDate], [StatusArticle], [OldStatus]) VALUES (N'vted', N'hoc toan', N'sdfga', N'quynh@gmail.com', CAST(N'2017-08-19 17:08:09.000' AS DateTime), N'Active', N'')
INSERT [dbo].[Comments] ([IDComment], [Name], [Comment], [Title]) VALUES (1, N'LittleMozartHVer@gmail.com', N'Kho', N'Dekiru')
INSERT [dbo].[Comments] ([IDComment], [Name], [Comment], [Title]) VALUES (2, N'hoang_25@gmail.com.vn', N'De', N'Dekiru')
INSERT [dbo].[Comments] ([IDComment], [Name], [Comment], [Title]) VALUES (3, N'quynh@gmail.com', N'So qua', N'The conjuring')
INSERT [dbo].[Comments] ([IDComment], [Name], [Comment], [Title]) VALUES (4, N'LittleMozartHVer@gmail.com', N'So qua', N'The conjuring')
INSERT [dbo].[Comments] ([IDComment], [Name], [Comment], [Title]) VALUES (5, N'quynh@gmail.com', N'kho qua', N'Dekiru')
INSERT [dbo].[Comments] ([IDComment], [Name], [Comment], [Title]) VALUES (6, N'quynh@gmail.com', N'qua hay', N'The conjuring')
INSERT [dbo].[Comments] ([IDComment], [Name], [Comment], [Title]) VALUES (7, N'LittleMozartHVer@gmail.com', N'thay Nam dep trai', N'vted')
INSERT [dbo].[Comments] ([IDComment], [Name], [Comment], [Title]) VALUES (8, N'hoang_25@gmail.com.vn', N'bai tap kho qua', N'vted')
INSERT [dbo].[Comments] ([IDComment], [Name], [Comment], [Title]) VALUES (10, N'quynh@gmail.com', N'hihihihi', N'Thep da toi the day')
INSERT [dbo].[Comments] ([IDComment], [Name], [Comment], [Title]) VALUES (11, N'quynh@gmail.com', N'hiha', N'Phia tay bien gioi phia nam mat troi')
INSERT [dbo].[Comments] ([IDComment], [Name], [Comment], [Title]) VALUES (12, N'hoang@gmail.com', N'abc', N'Doi thay doi khi chung ta thay doi')
INSERT [dbo].[Comments] ([IDComment], [Name], [Comment], [Title]) VALUES (13, N'quynh@gmail.com', N'khong hieu gi', N'Code dao')
INSERT [dbo].[Comments] ([IDComment], [Name], [Comment], [Title]) VALUES (14, N'quynh@gmail.com', N'hay hay hay', N'Doi thay doi khi chung ta thay doi')
INSERT [dbo].[Comments] ([IDComment], [Name], [Comment], [Title]) VALUES (15, N'quynh@gmail.com', N'taidol', N'Phia tay bien gioi phia nam mat troi')
INSERT [dbo].[Comments] ([IDComment], [Name], [Comment], [Title]) VALUES (16, N'quynh@gmail.com', N'bi an', N'Mat ma Da Vinci')
INSERT [dbo].[Comments] ([IDComment], [Name], [Comment], [Title]) VALUES (17, N'quynh@gmail.com', N'hahahihi', N'Sherlock Holmes')
INSERT [dbo].[Comments] ([IDComment], [Name], [Comment], [Title]) VALUES (18, N'hoang_25@gmail.com.vn', N'goku', N'Dragon Balls')
INSERT [dbo].[Comments] ([IDComment], [Name], [Comment], [Title]) VALUES (19, N'hoang_25@gmail.com.vn', N'ahaha', N'Doi thay doi khi ta thay doi')
INSERT [dbo].[Comments] ([IDComment], [Name], [Comment], [Title]) VALUES (20, N'quynh@gmail.com', N'asdf gfd', N'Rich dad, Poor dad')
INSERT [dbo].[Comments] ([IDComment], [Name], [Comment], [Title]) VALUES (21, N'quynh@gmail.com', N'hihi', N'Code learn')
INSERT [dbo].[Comments] ([IDComment], [Name], [Comment], [Title]) VALUES (22, N'quynh@gmail.com', N'aaa', N'Code learn')
INSERT [dbo].[Comments] ([IDComment], [Name], [Comment], [Title]) VALUES (23, N'quynh@gmail.com', N'haha', N'Naruto')
INSERT [dbo].[Comments] ([IDComment], [Name], [Comment], [Title]) VALUES (24, N'quynh@gmail.com', N'hihi', N'Naruto')
INSERT [dbo].[Comments] ([IDComment], [Name], [Comment], [Title]) VALUES (25, N'hoang@gmail.com', N'hihi', N'Naruto')
INSERT [dbo].[Comments] ([IDComment], [Name], [Comment], [Title]) VALUES (26, N'adung5341@gmail.com', N'hihi', N'Naruto')
INSERT [dbo].[Comments] ([IDComment], [Name], [Comment], [Title]) VALUES (27, N'quynh@gmail.com', N'hihi', N'Naruto')
INSERT [dbo].[Comments] ([IDComment], [Name], [Comment], [Title]) VALUES (28, N'quynh@gmail.com', N'hihi', N'Naruto')
INSERT [dbo].[Comments] ([IDComment], [Name], [Comment], [Title]) VALUES (29, N'quynh@gmail.com', N'hihi', N'Naruto')
INSERT [dbo].[Comments] ([IDComment], [Name], [Comment], [Title]) VALUES (30, N'quynh@gmail.com', N'hihi', N'Naruto')
INSERT [dbo].[Comments] ([IDComment], [Name], [Comment], [Title]) VALUES (31, N'quynh@gmail.com', N'hooo', N'Naruto')
INSERT [dbo].[Comments] ([IDComment], [Name], [Comment], [Title]) VALUES (32, N'quynh@gmail.com', N'hix', N'Naruto')
INSERT [dbo].[Comments] ([IDComment], [Name], [Comment], [Title]) VALUES (33, N'quynh@gmail.com', N'hix', N'Naruto')
INSERT [dbo].[Comments] ([IDComment], [Name], [Comment], [Title]) VALUES (34, N'quynh@gmail.com', N'hixx', N'Naruto')
INSERT [dbo].[Registration] ([Email], [Name], [Password], [Role], [Status]) VALUES (N'adung5341@gmail.com', N'Giang Luu Anh Dung', N'57263070896089522454993486766934034343575232894805004723529523458717411356833', N'admin', N'New')
INSERT [dbo].[Registration] ([Email], [Name], [Password], [Role], [Status]) VALUES (N'hoang@gmail.com', N'hoang', N'64042235640975155117310274771932083755136637533499687061408327255432019864722', N'member', N'New')
INSERT [dbo].[Registration] ([Email], [Name], [Password], [Role], [Status]) VALUES (N'hoang_25@gmail.com.vn', N'Thanh Hoang', N'81452445132958007976197370139763327838197144953952068970069325316357323863613', N'member', N'New')
INSERT [dbo].[Registration] ([Email], [Name], [Password], [Role], [Status]) VALUES (N'LittleMozartHVer@gmail.com', N'Dung', N'57263070896089522454993486766934034343575232894805004723529523458717411356833', N'member', N'New')
INSERT [dbo].[Registration] ([Email], [Name], [Password], [Role], [Status]) VALUES (N'nam@gmail.com.vn', N'Nam Giang', N'110842930636301503431078489248955086146866179993383765826251450222918537854319', N'member', N'New')
INSERT [dbo].[Registration] ([Email], [Name], [Password], [Role], [Status]) VALUES (N'quynh@gmail.com', N'Quynh', N'102855299303953274444528624871805846810018238304272428875583499896650629938781', N'member', N'New')
ALTER TABLE [dbo].[Article]  WITH CHECK ADD  CONSTRAINT [FK_Article_Registration] FOREIGN KEY([Author])
REFERENCES [dbo].[Registration] ([Email])
GO
ALTER TABLE [dbo].[Article] CHECK CONSTRAINT [FK_Article_Registration]
GO
ALTER TABLE [dbo].[Comments]  WITH CHECK ADD  CONSTRAINT [FK_Comments_Article] FOREIGN KEY([Title])
REFERENCES [dbo].[Article] ([Title])
GO
ALTER TABLE [dbo].[Comments] CHECK CONSTRAINT [FK_Comments_Article]
GO
ALTER TABLE [dbo].[Comments]  WITH CHECK ADD  CONSTRAINT [FK_Comments_Registration] FOREIGN KEY([Name])
REFERENCES [dbo].[Registration] ([Email])
GO
ALTER TABLE [dbo].[Comments] CHECK CONSTRAINT [FK_Comments_Registration]
GO
USE [master]
GO
ALTER DATABASE [SimpleBlog] SET  READ_WRITE 
GO
