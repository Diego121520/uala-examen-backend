INSERT INTO users (username)
VALUES ('user1'), ('user2'), ('user3'), ('user4'), ('user5'), ('user6'), ('user7'), ('user8'), ('user9'), ('user10');

INSERT INTO tweets (message, created_at, user_id) VALUES ('Tweet 1 by user1', NOW(), 1),('Tweet 2 by user1', NOW(), 1),('Tweet 1 by user2', NOW(), 2),('Tweet 2 by user2', NOW(), 2),('Tweet 1 by user4', NOW(), 4),('Tweet 1 by user5', NOW(), 5),('Tweet 1 by user6', NOW(), 6),('Tweet 1 by user7', NOW(), 7),('Tweet 1 by user8', NOW(), 8),('Tweet 1 by user9', NOW(), 9),('Tweet 1 by user10', NOW(), 10);

INSERT INTO user_followers (following_id, user_id) VALUES (2, 1), (3, 1), (4, 1), (5, 1), (6, 1), (7, 1), (8, 1), (9, 1), (10, 1), (1, 2), (3, 2), (4, 2), (5, 2), (6, 2), (7, 2), (8, 2), (9, 2), (10, 2)