SELECT * FROM elearning.courses;
use elearning;
INSERT INTO courses
	(active, createdBy, createdDate, updatedBy, updatedDate, category_id, content, description, hour_count, image, lectures_count, price, promotion_price, title, discount)
VALUES(1,'admin@gmail.com',now(),'admin@gmail.com',now(),1,'test','description',4,'image_path',10,1000,10,'test_title',10);                      