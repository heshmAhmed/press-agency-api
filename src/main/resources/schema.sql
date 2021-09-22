CREATE TABLE post_type(
    id INTEGER AUTO_INCREMENT PRIMARY KEY,
    type varchar(20) NOT NULL unique
);

CREATE TABLE role(
    id INTEGER AUTO_INCREMENT PRIMARY KEY,
    role varchar(20) NOT NULL unique
);

CREATE TABLE actor (
    id INTEGER AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    email VARCHAR(250)NOT NULL,
    password varchar(250) NOT NULL,
    phone varchar(20) NOT NULL,
    photo varchar(250),
    username varchar(250) NOT NULL ,
    role_id INTEGER NOT NULL,
    FOREIGN KEY (role_id) REFERENCES role(id) ON DELETE CASCADE ON UPDATE CASCADE
);
CREATE unique INDEX index_email ON actor (email);
CREATE unique INDEX index_username ON actor (username);

CREATE TABLE post(
    id INTEGER AUTO_INCREMENT PRIMARY KEY,
    editor_id INTEGER NOT NULL ,
    editor_name varchar(250) NOT NULL,
    title varchar(250) NOT NULL,
    body varchar(500) NOT NULL,
    no_views INTEGER NOT NULL,
    no_likes INTEGER NOT NULL,
    no_dislikes INTEGER NOT NULL,
    create_date datetime NOT NULL ,
    state boolean,
    type_id Integer NOT NULL ,
    FOREIGN KEY (editor_id) REFERENCES actor(id) ON DELETE CASCADE ON UPDATE CASCADE ,
    FOREIGN KEY (type_id) REFERENCES post_type(id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE saved_post(
    viewer_id INTEGER NOT NULL,
    post_id INTEGER NOT NULL,
    create_date datetime NOT NULL ,
    FOREIGN KEY (viewer_id) REFERENCES actor(id) ON DELETE CASCADE ON UPDATE CASCADE ,
    FOREIGN KEY (post_id) REFERENCES post(id) ON DELETE CASCADE ON UPDATE CASCADE ,
    primary key (viewer_id, post_id)
);

CREATE TABLE interaction(
    viewer_id INTEGER NOT NULL,
    post_id INTEGER NOT NULL,
    is_like boolean NOT NULL ,
    PRIMARY KEY (viewer_id, post_id),
    FOREIGN KEY (viewer_id) REFERENCES actor(id) ON DELETE CASCADE ON UPDATE CASCADE ,
    FOREIGN KEY (post_id) REFERENCES  post(id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE question(
    id INTEGER AUTO_INCREMENT PRIMARY KEY,
    post_id INTEGER NOT NULL,
    viewer_id INTEGER NOT NULL,
    body VARCHAR(250) NOT NULL ,
    create_date datetime NOT NULL ,
    FOREIGN KEY (post_id) REFERENCES post(id) ON DELETE CASCADE ON UPDATE CASCADE ,
    FOREIGN KEY (viewer_id) REFERENCES actor(id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE answer(
    id INTEGER AUTO_INCREMENT PRIMARY KEY,
    question_id INTEGER NOT NULL,
    actor_id INTEGER NOT NULL,
    actor_name VARCHAR(250) NOT NULL,
    body text Not Null,
    create_date datetime NOT NULL,
    FOREIGN KEY (question_id) REFERENCES question(id) ON DELETE CASCADE ON UPDATE CASCADE ,
    FOREIGN KEY (actor_id) REFERENCES actor(id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TRIGGER after_insert_interaction
    AFTER INSERT ON interaction
    FOR EACH ROW
    BEGIN
         IF NEW.is_like = 1 THEN
             update post set no_likes = no_likes + 1 where post.id = NEW.post_id;
        ELSE
             update post set no_dislikes = no_dislikes + 1 where post.id = NEW.post_id;
         end if;
    END;

CREATE TRIGGER after_update_interaction
    AFTER UPDATE ON interaction
    FOR EACH ROW
    BEGIN
        IF NEW.is_like != OLD.is_like THEN
            IF NEW.is_like = 1 THEN
                update post set no_likes = no_likes + 1, no_dislikes = no_dislikes - 1 where id = NEW.post_id;
            ELSE
                update post set no_dislikes = no_dislikes + 1, no_likes = no_likes - 1 where id = NEW.post_id;
            END IF;
        END IF;
    END;

CREATE TRIGGER after_delete_interaction
    AFTER DELETE ON interaction
    FOR EACH ROW
    BEGIN
        IF OLD.is_like = 1 THEN
            update post set no_likes = no_likes - 1 where id = OLD.post_id;
        ELSE
            update post set  no_dislikes = no_dislikes - 1 where id = OLD.post_id;
        END IF;
    END;
