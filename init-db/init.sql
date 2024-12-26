create table if not exists establishment
(
    id              bigint,
    name            VARCHAR(255) not null,
    address         VARCHAR(255),
    phone_number    VARCHAR(32),
    email           VARCHAR(255),
    social_network  VARCHAR(255),
    additional_info VARCHAR(2000),
    description     VARCHAR(2000),
    latitude        float(53),
    longitude       float(53),
    poster          bytea,
    login           VARCHAR(255),
    password        VARCHAR(255),
    constraint pk_establishment primary key (id)
);

create sequence if not exists establishment_id_seq increment by 1;

create table if not exists establishment_working_day
(
    id               bigint,
    start_hour       int    not null,
    end_hour         int    not null,
    day_of_week      int    not null,
    establishment_id bigint not null,
    foreign key (establishment_id) references establishment (id),
    constraint pk_establishment_working_day primary key (id)
);

create sequence if not exists establishment_working_day_id_seq increment by 1;

create index if not exists idx_est_working_day_est_id on establishment_working_day (establishment_id);

create table if not exists sales
(
    id                bigint,
    name              VARCHAR(255)  not null,
    description       VARCHAR(2000) not null,
    short_description VARCHAR(255),
    poster            bytea,
    qr                VARCHAR(255)/*should be not null later...*/,
    establishment_id  bigint        not null,
    foreign key (establishment_id) references establishment (id),
    constraint pk_sales primary key (id)
);

create sequence if not exists sales_id_seq increment by 1;

create index if not exists idx_sales_est_id on sales (establishment_id);

create table if not exists reward
(
    id          bigint,
    reward_text VARCHAR(500) not null,
    sales_id    bigint       not null,
    foreign key (sales_id) references sales (id),
    constraint pk_reward primary key (id)
);

create sequence if not exists reward_id_seq increment by 1;

create index if not exists idx_reward_sales_id on reward (sales_id);

create table if not exists manual_condition
(
    id             bigint,
    condition_text VARCHAR(2000) not null,
    sales_id       bigint        not null,
    foreign key (sales_id) references sales (id),
    constraint pk_manual_condition primary key (id)
);

create sequence if not exists manual_condition_id_seq increment by 1;

create index if not exists idx_manual_condition_sales_id on manual_condition (sales_id);

create table if not exists time_restriction
(
    id         bigint,
    start_date DATE   not null,
    end_date   DATE   not null,
    sales_id   bigint not null,
    foreign key (sales_id) references sales (id),
    constraint pk_time_restriction primary key (id)
);

create sequence if not exists time_restriction_id_seq increment by 1;

create index if not exists idx_time_restriction_sales_id on time_restriction (sales_id);

create table if not exists dealfinder_user
(
    id       bigint,
    username VARCHAR(50) not null,
    password VARCHAR(50) not null,
    login    VARCHAR(50) not null unique,
    source   varchar(100),
    constraint pk_dealfinder_user primary key (id)
);

create sequence if not exists dealfinder_user_id_seq increment by 1;

create table if not exists user_sales
(
    id        bigint,
    completed boolean not null default false,
    sales_id  bigint  not null,
    user_id   bigint  not null,
    foreign key (sales_id) references sales (id),
    foreign key (user_id) references dealfinder_user (id),
    constraint pk_user_sales primary key (id)
);

create sequence if not exists user_sales_id_seq increment by 1;

create index if not exists idx_user_sales_sales_id on user_sales (sales_id);
create index if not exists idx_user_sales_user_id on user_sales (user_id);

create table if not exists user_manual_condition
(
    id                  bigint,
    completed           boolean not null default false,
    user_sales_id       bigint  not null,
    manual_condition_id bigint  not null,
    foreign key (user_sales_id) references user_sales (id),
    foreign key (manual_condition_id) references manual_condition (id),
    constraint pk_user_manual_condition primary key (id)
);

create sequence if not exists user_manual_condition_id_seq increment by 1;

create index if not exists idx_user_manual_condition_user_sales_id on user_manual_condition (user_sales_id);
create index if not exists idx_user_manual_condition_manual_condition_id on user_manual_condition (manual_condition_id);

create table if not exists progress_condition
(
    id            bigint,
    product_name  VARCHAR(255) not null,
    needed_amount int          not null,
    sales_id      bigint       not null,
    foreign key (sales_id) references sales (id),
    constraint pk_progress_condition primary key (id)
);

create sequence if not exists progress_condition_id_seq increment by 1;

create index if not exists idx_progress_condition_sales_id on progress_condition (sales_id);

create table if not exists user_progress_condition
(
    id                    bigint,
    current_amount        int     not null default 0,
    completed             boolean not null default false,
    user_sales_id         bigint  not null,
    progress_condition_id bigint  not null,
    foreign key (user_sales_id) references user_sales (id),
    foreign key (progress_condition_id) references progress_condition (id),
    constraint pk_user_progress_condition primary key (id)
);

create sequence if not exists user_progress_condition_id_seq increment by 1;

create index if not exists idx_user_progress_condition_user_sales_id on user_progress_condition (user_sales_id);
create index if not exists idx_user_progress_condition_progress_condition_id on user_progress_condition (progress_condition_id);

create table if not exists hours_restriction
(
    id       bigint,
    sales_id bigint not null,
    foreign key (sales_id) references sales (id),
    constraint pk_hours_restriction primary key (id)
);

create sequence if not exists hours_restriction_id_seq increment by 1;

create index if not exists idx_hours_restriction_sales_id on hours_restriction (sales_id);

create table if not exists single_day_hours_restriction
(
    id                   bigint,
    start_hour           int    not null,
    end_hour             int    not null,
    day_of_week          int    not null,
    hours_restriction_id bigint not null,
    foreign key (hours_restriction_id) references hours_restriction (id),
    constraint pk_single_day_hours_restriction primary key (id)
);

create sequence if not exists single_day_hours_restriction_id_seq increment by 1;

create index if not exists idx_single_day_hours_restr_hours_restr_id on hours_restriction (sales_id);

create table if not exists chat_data
(
    id         bigint,
    chat_id    bigint       not null,
    chat_state varchar(255) not null,
    updated_at timestamp    not null,
    constraint pk_chat_data primary key (id)
);

create sequence if not exists chat_data_id_seq increment by 1;

INSERT INTO dealfinder_user (id, username, password, login)
VALUES (100001, 'rodionov', '1', 'rodionov'),
       (100002, 'shirokorad', '1', 'shirokorad'),
       (100003, 'salahov', '1', 'salahov');


INSERT INTO establishment
VALUES (100001, 'Порт-Брю', 'Морской пр., 44, Новосибирск, Новосибирская обл., 630090', '+7923-775-57-80', 'portbrew@portbrew', 'лалала', 'Ждём Вас каждый день в наших кофейнях', 'Ждём Вас каждый день в наших кофейнях',
        54.836077, 83.099347, pg_read_binary_file('/docker-entrypoint-initdb.d/data/port-bru.jpg')::bytea, 'establishment1', '1111'),
       (100002, 'Чашка кофе', 'Морской пр., 54, Новосибирск, Новосибирская обл., 630090', '8 (383) 333-03-82', '© Чашка кофе,', 'd.rodionov@g.nsu.ru', '"Чашка Кофе" - известная Новосибирская сеть, входящая в группу ресторанов Дениса Иванова.', '"Чашка Кофе" - известная Новосибирская сеть, входящая в группу ресторанов Дениса Иванова.',
        54.835237, 83.097299, pg_read_binary_file('/docker-entrypoint-initdb.d/data/chashka-coffe.jpg')::bytea, 'establishment2', '1111'),
       (100003, 'Академия кофе', '3 description', 'test address', '88005553535', 'd.rodionov@g.nsu.ru', '@mrxat',
        'noting', 54.840853, 83.110118,
        pg_read_binary_file('/docker-entrypoint-initdb.d/data/academiya-coffe.jpg')::bytea, 'establishment3', '1111'),
       (100004, 'Super Coffee', '4 description', 'test address', '88005553535', 'd.rodionov@g.nsu.ru', '@mrxat',
        'noting', 54.839216, 83.095121, pg_read_binary_file('/docker-entrypoint-initdb.d/data/super-coffe.jpg')::bytea,
        'establishment4', '1111');


INSERT INTO sales (id, name, description, short_description, poster, qr, establishment_id)
VALUES
    (100001, 'Специальное предложение: Кофе с пончиком', 'Приглашаем вас насладиться ароматным кофе и вкусным пончиком! В нашем кафе вы можете заказать любой напиток и получить пончик в подарок.', 'Кофе с пончиком', pg_read_binary_file('/docker-entrypoint-initdb.d/data/акция1.jpg')::bytea, 'dealfinder-processor/src/main/resources/QR/qr_code_100001.png', 100001),
    (100002, 'Скидка 20% на любой напиток', 'Специальное предложение для наших постоянных клиентов! Получите скидку 20% на любой напиток в нашей кофейне.', 'Скидка 20% на напиток', pg_read_binary_file('/docker-entrypoint-initdb.d/data/акция2.jpg')::bytea, 'dealfinder-processor/src/main/resources/QR/qr_code_100002.png', 100002),
    (100003, 'Двойные бонусы за каждую покупку', 'Принимайте участие в нашей акции и получайте двойные бонусы за каждую покупку! Не упустите возможность получить больше приятных подарков.', 'Двойные бонусы', pg_read_binary_file('/docker-entrypoint-initdb.d/data/акция3.jpg')::bytea, 'dealfinder-processor/src/main/resources/QR/qr_code_100003.png', 100003),
    (100004, 'Кофе на вынос со скидкой 30%', 'Хотите пить вкусный кофе в любом месте? Закажите кофе на вынос и получите скидку 30%!', 'Скидка 30% на кофе на вынос', pg_read_binary_file('/docker-entrypoint-initdb.d/data/акция4.jpg')::bytea, 'dealfinder-processor/src/main/resources/QR/qr_code_100004.png', 100004),
    (100005, 'Торт в подарок к каждому заказу', 'Приглашаем вас насладиться нашими десертами! Сделайте заказ в нашей кофейне и получите в подарок вкусный торт.', 'Торт в подарок', pg_read_binary_file('/docker-entrypoint-initdb.d/data/акция4.jpg')::bytea, 'dealfinder-processor/src/main/resources/QR/qr_code_100005.png', 100004);

INSERT INTO manual_condition (id, condition_text, sales_id)
VALUES (100001, 'Больше 18 лет', 100001),
       (100002, 'День рождение', 100001),
       (100003, 'Компания от 3-х человек', 100002),
       (100004, 'Manual Condition 3', 100003),
       (100005, 'Manual Condition 4', 100004),
       (100006, 'Manual Condition 5', 100005),
       (100007, 'Manual Condition 1.1', 100001);

INSERT INTO user_sales (id, completed, sales_id, user_id)
VALUES (100001, true, 100001, 100001),
       (100002, true, 100002, 100001),
       (100003, true, 100003, 100002),
       (100004, false, 100004, 100002);



INSERT INTO user_manual_condition (id, completed, user_sales_id, manual_condition_id)
VALUES (100001, true, 100001, 100001),
       (100002, true, 100001, 100002),
       (100003, false, 100001, 100007),
       (100004, false, 100002, 100003),
       (100005, false, 100003, 100004),
       (100006, true, 100004, 100005);



INSERT INTO progress_condition (id, product_name, needed_amount, sales_id)
VALUES (100001, 'Кофе', 10, 100001),
       (100002, 'Латте', 20, 100002),
       (100003, 'Черный чай', 30, 100003),
       (100004, 'Пицца', 40, 100004),
       (100005, 'Пирожки', 50, 100005);

INSERT INTO user_progress_condition (id, current_amount, completed, user_sales_id, progress_condition_id)
VALUES (100001, 5, true, 100001, 100001),
       (100002, 25, false, 100002, 100002),
       (100003, 15, false, 100003, 100003),
       (100004, 20, false, 100004, 100004);

INSERT INTO reward (id, reward_text, sales_id)
VALUES (100001, 'Премиальный подарок за активное участие', 100001),
       (100002, 'Призовой купон на скидку в партнерских магазинах', 100001),
       (100003, 'Подарочный сертификат на выбор товара', 100002),
       (100004, 'Уникальный подарок от бренда', 100002),
       (100005, 'Дополнительный бонусный балл в программе лояльности', 100003),
       (100006, 'Бесплатное пиво', 100003),
       (100007, 'Пицца в подарок', 100004),
       (100008, '300 бонусных рублей', 100004),
       (100009, 'Кофе в подарок', 100005),
       (1000010, 'Два банана в подарок', 100005);
