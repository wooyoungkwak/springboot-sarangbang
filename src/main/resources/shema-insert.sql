insert into Person (Account, Password, Role, FirstName, LastName) values
('young', 'qwer1234', 1, 'kwak', 'wooyoung');

insert into BangInfo
(room, BathRoom, deal, floor, Place, ground, Location, heating, addressSeq, address, direction,
 price, registerDate, EstateAgencySeq)
values
    (5, 2, '매매', 2, 2752, 2540, '월산동', '개별난방 도시가스', 1, '월산동 무드시장 농협부근', '동향',
     160000000, '1982-07-21', 1);

insert into EstateAgency (AddressSeq, Address, name, phone, agencyName, agencyPhone) values
(1, '광주 서구 경열로 73', '로또공인중개사무소', '010-3747-2688', '최미림', '010-3747-2688' );


--------------

insert into StockInfo (StockType, Price, HighPrice, LowPrice, regDate, StockCompanySeq) values
    (1, '469000', '472000', '462000', '2022-03-18 16:00', 1),
    (1, '445500', '457000', '443000', '2022-03-14 16:00', 1),
    (1, '439500', '444500', '444000', '2022-03-15 16:00', 1),
    (1, '441500', '444500', '436000', '2022-03-16 16:00', 1),
    (1, '469000', '475000', '448500', '2022-03-17 16:00', 1);


insert into StockInfo (StockType, Price, HighPrice, LowPrice, regDate, StockCompanySeq) values
                                                                                            (1, '0', '0', '0', '2022-03-19 16:00', 1),
                                                                                            (1, '0', '0', '0', '2022-03-20 16:00', 1),
                                                                                            (1, '464000', '476500', '464000', '2022-03-21 16:00', 1),
                                                                                            (1, '463000', '466000', '461000', '2022-03-22 16:00', 1),
                                                                                            (1, '469000', '473500', '467500', '2022-03-23 16:00', 1);

--------------
insert into StockCompany (name, address, birthday) values
    ('nc','경기도 성남시 분당구 대왕판교로644번길 12 엔씨소프트','1997-03-17' ), ('samsung','경기도 수원시 영통구 삼성로 129(매탄동)','1969-01-13' ),('lg','서울특별시 영등포구 여의대로 128
(여의도동) LG트윈타워','1958-10-01');




