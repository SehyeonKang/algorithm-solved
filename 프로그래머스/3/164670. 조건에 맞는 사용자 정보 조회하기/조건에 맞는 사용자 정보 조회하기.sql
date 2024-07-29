-- 코드를 입력하세요
SELECT U.USER_ID, U.NICKNAME, CONCAT(CITY, " ", STREET_ADDRESS1, " ", STREET_ADDRESS2) AS '전체주소',
CONCAT(SUBSTR(U.TLNO, 1, 3), "-", SUBSTR(U.TLNO, 4, 4), "-", SUBSTR(U.TLNO, 8, 4)) AS '전화번호'
FROM USED_GOODS_BOARD AS B JOIN USED_GOODS_USER AS U
ON B.WRITER_ID = U.USER_ID
GROUP BY U.USER_ID
HAVING COUNT(U.USER_ID) >= 3
ORDER BY U.USER_ID DESC;