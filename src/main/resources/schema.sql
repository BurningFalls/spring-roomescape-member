DROP TABLE IF EXISTS reservation;
DROP TABLE IF EXISTS reservation_time;
DROP TABLE IF EXISTS theme;

CREATE TABLE reservation_time
(
    id       BIGINT       NOT NULL AUTO_INCREMENT,
    start_at VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

INSERT INTO reservation_time (start_at)
VALUES ('09:00'),
       ('10:00'),
       ('11:00'),
       ('12:00'),
       ('13:00'),
       ('14:00'),
       ('15:00'),
       ('16:00'),
       ('17:00'),
       ('18:00');

CREATE TABLE theme
(
    id          BIGINT       NOT NULL AUTO_INCREMENT,
    name        VARCHAR(255) NOT NULL,
    description VARCHAR(255) NOT NULL,
    thumbnail   VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

INSERT INTO theme (name, description, thumbnail)
VALUES ('정글 모험', '열대 정글의 심연을 탐험하세요.', 'https://example.com/thumbnails/jungle.jpg'),
       ('우주 오디세이', '별들을 넘어 우주 여행을 떠나세요.', 'https://example.com/thumbnails/space.jpg'),
       ('보물 찾기', '잃어버린 보물을 찾아 모험을 떠나세요.', 'https://example.com/thumbnails/treasure.jpg'),
       ('미스터리 저택', '저택 안에 숨겨진 미스터리를 풀어보세요.', 'https://example.com/thumbnails/mystery.jpg'),
       ('해적의 만', '바다를 항해하며 묻힌 금을 찾아보세요.', 'https://example.com/thumbnails/pirate.jpg'),
       ('유령의 할로윈', '유령의 집에서 스릴과 오싹함을 경험하세요.', 'https://example.com/thumbnails/halloween.jpg'),
       ('겨울 왕국', '눈 덮인 풍경의 마법과 아름다움을 즐기세요.', 'https://example.com/thumbnails/winter.jpg'),
       ('슈퍼히어로 사가', '슈퍼히어로가 되어 도시를 악에서 구하세요.', 'https://example.com/thumbnails/superhero.jpg'),
       ('역사적 탐험', '역사적 장소를 탐험하며 과거로 여행하세요.', 'https://example.com/thumbnails/historical.jpg'),
       ('사막 탈출', '뜨거운 사막을 지나 탈출을 시도하세요.', 'https://example.com/thumbnails/desert.jpg');


CREATE TABLE reservation
(
    id       BIGINT       NOT NULL AUTO_INCREMENT,
    name     VARCHAR(255) NOT NULL,
    date     VARCHAR(255) NOT NULL,
    time_id  BIGINT       NOT NULL,
    theme_id BIGINT       NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (time_id) REFERENCES reservation_time (id),
    FOREIGN KEY (theme_id) REFERENCES theme (id)
);
