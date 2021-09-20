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
    FOREIGN KEY (role_id) REFERENCES role(id)
);

CREATE TABLE post(
    id INTEGER AUTO_INCREMENT PRIMARY KEY,
    editor_id INTEGER NOT NULL ,
    editor_name varchar(250) NOT NULL,
    title varchar(250) NOT NULL,
    body varchar(500) NOT NULL,
    no_views INTEGER NOT NULL,
    no_likes INTEGER NOT NULL,
    no_dislikes INTEGER NOT NULL,
    create_date datetime,
    state boolean,
    type_id Integer NOT NULL ,
    FOREIGN KEY (editor_id) REFERENCES actor(id),
    FOREIGN KEY (type_id) REFERENCES post_type(id)
);

CREATE TABLE saved_post(
    viewer_id INTEGER NOT NULL,
    post_id INTEGER NOT NULL,
    create_date datetime,
    FOREIGN KEY (viewer_id) REFERENCES actor(id),
    FOREIGN KEY (post_id) REFERENCES post(id),
    primary key (viewer_id, post_id)
);

CREATE TABLE interaction(
    viewer_id INTEGER NOT NULL,
    post_id INTEGER NOT NULL,
    is_like boolean,
    PRIMARY KEY (viewer_id, post_id),
    FOREIGN KEY (viewer_id) REFERENCES actor(id),
    FOREIGN KEY (post_id) REFERENCES  post(id)
);

CREATE TABLE question(
    id INTEGER AUTO_INCREMENT PRIMARY KEY,
    post_id INTEGER NOT NULL,
    viewer_id INTEGER NOT NULL,
    body VARCHAR(250) NOT NULL ,
    create_date datetime NOT NULL ,
    FOREIGN KEY (post_id) REFERENCES post(id),
    FOREIGN KEY (viewer_id) REFERENCES actor(id)
);

CREATE TABLE answer(
    id INTEGER AUTO_INCREMENT PRIMARY KEY,
    question_id INTEGER NOT NULL,
    body text Not Null,
    create_date datetime NOT NULL,
    FOREIGN KEY (question_id) REFERENCES question(id)
);

