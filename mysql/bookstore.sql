use bookstore;


create table roles (
	id int auto_increment,
    role_name varchar(10),
    primary key(id)
);

create table users(
	id int auto_increment,
    first_name nvarchar(20),
    last_name nvarchar(20),
    email varchar(20),
    address nvarchar(50),
    created_at timestamp,
    phone varchar(15),
    role_id int,
    primary key(id)
);

create table author(
	id int auto_increment,
    author_name nvarchar(50),
    bio nvarchar(50),
    email varchar(20),
    address nvarchar(50),
    phone varchar(15),
    primary key(id)
);

create table category(
	id int auto_increment,
    category_name nvarchar(20),
    primary key(id)
);

create table book(
	id int auto_increment,
    book_name nvarchar(20),
    book_description nvarchar(50),
    book_page int,
    image_book text,
    reprint int,
    price decimal(10, 2),
    category_id int,
    author_id int,
    primary key(id)
);

create table store(
	id int auto_increment,
    store_name nvarchar(20),
    store_description nvarchar(50),
    address nvarchar(50),
    image_store text,
    email varchar(20),
    phone varchar(15),
    open_date timestamp,
    primary key(id)
);

create table store_book(
	store_id int,
    book_id int,
    primary key(store_id, book_id)
);


create table rating_book(
	id int auto_increment,
	book_id int,
    user_id int,
    content nvarchar(50),
    rate_point int,
    created_at timestamp,
    primary key(id)
   
);


create table rating_store(
	id int auto_increment,
	user_id int,
    store_id int,
    content nvarchar(50),
    rate_point int,
    created_at timestamp,
    primary key(id)
    
);

create table order_book(
	id int auto_increment,
	user_id int,
    store_id int,
    ordered_at timestamp,
    price decimal(10, 2),
    primary key(id)
);

create table order_book_detail(
	order_id int,
    book_id int,
    quantity int,
    primary key(order_id, book_id)
);

alter table users add constraint fk_users_role_id foreign key(role_id) references roles(id);
alter table order_book add constraint fk_order_book_user_id foreign key(user_id) references users(id);
alter table order_book add constraint fk_order_book_store_id foreign key(store_id) references store(id);
alter table rating_book add constraint fk_rating_book_user_id foreign key(user_id) references users(id);
alter table rating_store add constraint fk_rating_store_user_id foreign key(user_id) references users(id);
alter table rating_book add constraint fk_rating_book_book_id foreign key(book_id) references book(id);
alter table book add constraint fk_book_author_id foreign key(author_id) references author(id);
alter table book add constraint fk_book_category_id foreign key(category_id) references category(id);
alter table rating_store add constraint fk_rating_store_store_id foreign key(store_id) references store(id);
alter table store_book add constraint fk_store_book_store_id foreign key(store_id) references store(id);
alter table store_book add constraint fk_store_book_book_id foreign key(book_id) references book(id);
alter table order_book_detail add constraint fk_order_book_detail_order_id foreign key(order_id) references order_book(id);
alter table order_book_detail add constraint fk_order_book_detail_book_id foreign key(book_id) references book(id);

alter table users add column username varchar(20);
alter table users add column password varchar(20);


INSERT INTO category (id, category_name) VALUES  
(1, "Văn học Việt Nam"),
(2, "Tiểu thuyết"),
(3, "Hồi ký"),
(4, "Triết học"),
(5, "Lịch sử"),
(6, "Thơ ca");


INSERT INTO author (author_name) VALUES
("Đoàn Giỏi"),
("Phùng Quán"),
("Nam Cao"),
("Tô Hoài"),
("Paulo Coelho"),
("Ngô Tất Tố"),
("Nguyễn Tuân"),
("Kim Lân"),
("Nguyễn Du"),
("Nguyễn Quang Sáng"),
("Nguyễn Trung Thành"),
("Nguyễn Công Hoan"),
("Vũ Trọng Phụng"),
("Nhất Linh"),
("Trần Văn Giàu"),
("Đặng Thùy Trâm"),
("Quang Dũng"),
("Hồ Chí Minh"),
("Nguyễn Dữ");

INSERT INTO book (book_name, book_description, book_page, image_book, reprint, price, category_id, author_id) VALUES
("Đất Rừng Phương Nam", "Cuộc phiêu lưu của cậu bé An", 280, "dat_rung_phuong_nam.jpg", 5, 80000.00, 1, 1),
("Tuổi Thơ Dữ Dội", "Chuyện về các chiến sĩ nhỏ tuổi", 350, "tuoi_tho_du_doi.jpg", 3, 90000.00, 1, 2),
("Chí Phèo", "Tác phẩm nổi tiếng của Nam Cao", 120, "chi_pheo.jpg", 8, 55000.00, 2, 3),
("Dế Mèn Phiêu Lưu Ký", "Cuộc hành trình của Dế Mèn", 200, "de_men_phieu_luu_ky.jpg", 7, 70000.00, 3, 4),
("Nhà Giả Kim", "Triết lý về cuộc sống và ước mơ", 250, "nha_gia_kim.jpg", 6, 85000.00, 4, 5),
("Tắt Đèn", "Câu chuyện của chị Dậu", 180, "tat_den.jpg", 4, 60000.00, 2, 6),
("Người Lái Đò Sông Đà", "Cuộc chiến của người lái đò với sông Đà", 170, "nguoi_lai_do_song_da.jpg", 3, 75000.00, 5, 7),
("Vợ Nhặt", "Câu chuyện bi thảm trong nạn đói", 130, "vo_nhat.jpg", 2, 50000.00, 2, 8),
("Truyện Kiều", "Tác phẩm kinh điển của Nguyễn Du", 250, "truyen_kieu.jpg", 10, 100000.00, 6, 9),
("Chiếc Lược Ngà", "Tình cha con trong chiến tranh", 160, "chiec_luoc_nga.jpg", 2, 65000.00, 5, 10),
("Rừng Xà Nu", "Biểu tượng của người Tây Nguyên", 190, "rung_xa_nu.jpg", 4, 68000.00, 1, 11),
("Lão Hạc", "Câu chuyện buồn của lão Hạc", 115, "lao_hac.jpg", 6, 52000.00, 2, 12),
("Tắt Đèn", "Cuộc sống khổ cực của nông dân", 200, "tat_den_2.jpg", 5, 75000.00, 2, 6),
("Số Đỏ", "Trào phúng cuộc sống thành thị", 210, "so_do.jpg", 7, 89000.00, 1, 13),
("Chuyện Tình Lan và Điệp", "Tình yêu bi kịch", 180, "lan_va_diep.jpg", 3, 64000.00, 5, 14),
("Ngoại Ô Đồng Khởi", "Cuộc chiến đấu ở ngoại ô", 195, "ngoai_o_dong_khoi.jpg", 2, 71000.00, 3, 15),
("Nhật Ký Đặng Thùy Trâm", "Cuộc sống và suy nghĩ của bác sĩ", 220, "nhat_ky_dang_thuy_tram.jpg", 4, 85000.00, 6, 16),
("Đôi Mắt Người Sơn Tây", "Tình yêu và chiến tranh", 150, "doi_mat_son_tay.jpg", 2, 63000.00, 4, 17),
("Đường Kách Mệnh", "Về cách mạng và kháng chiến", 300, "duong_kach_menh.jpg", 5, 95000.00, 3, 18),
("Người Con Gái Nam Xương", "Tình yêu và lòng trắc ẩn", 170, "nguoi_con_gai_nam_xuong.jpg", 3, 72000.00, 5, 19);

INSERT INTO store (store_name, store_description, address, image_store, email, phone, open_date) VALUES
("Store Hà Nội", "Cửa hàng tại Hà Nội", "123 Phố Huế, Hà Nội", "store_hanoi.jpg", "hanoi@store.vn", "0123456789", NOW()),
("Store Hồ Chí Minh", "Cửa hàng tại Hồ Chí Minh", "456 Nguyễn Huệ, TP.HCM", "store_hcm.jpg", "hcm@store.vn", "0987654321", NOW()),
("Store Đà Nẵng", "Cửa hàng tại Đà Nẵng", "789 Trần Phú, Đà Nẵng", "store_danang.jpg", "danang@store.vn", "0777888999", NOW()),
("Store Hải Phòng", "Cửa hàng tại Hải Phòng", "321 Lạch Tray, Hải Phòng", "store_haiphong.jpg", "haiphong@store.vn", "0666777888", NOW()),
("Store Cần Thơ", "Cửa hàng tại Cần Thơ", "654 Nguyễn Văn Linh, Cần Thơ", "store_cantho.jpg", "cantho@store.vn", "0555666777", NOW());


INSERT INTO store_book (store_id, book_id) VALUES
(1, 21), -- Đất Rừng Phương Nam
(1, 22), -- Chí Phèo
(1, 23), -- Nhà Giả Kim
(2, 24), -- Tuổi Thơ Dữ Dội
(2, 25), -- Dế Mèn Phiêu Lưu Ký
(2, 26), -- Tắt Đèn
(3, 27), -- Người Lái Đò Sông Đà
(3, 29), -- Truyện Kiều
(3, 30), -- Chiếc Lược Ngà
(4, 31), -- Vợ Nhặt
(4, 32), -- Rừng Xà Nu
(4, 33), -- Lão Hạc
(5, 34), -- Số Đỏ
(5, 35), -- Chuyện Tình Lan và Điệp
(5, 36); -- Ngoại Ô Đồng Khởi

use bookstore;
select * from users;
select * from roles;
select * from store;
select * from store_book;
select * from book;
select * from category;
select * from author;
select * from order_book;
select * from order_book_detail;
select * from rating_book;
select * from rating_store;
select * from token_invalid;


