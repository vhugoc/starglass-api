CREATE TABLE customer (
    id TEXT UNIQUE NOT NULL PRIMARY KEY,
    merchant_id TEXT NOT NULL references merchant(id),
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    is_active BOOLEAN DEFAULT true,
    name TEXT NOT NULL,
    postal_code TEXT DEFAULT NULL,
    street TEXT DEFAULT NULL,
    number TEXT DEFAULT NULL,
    complement TEXT DEFAULT NULL,
    neighborhood TEXT DEFAULT NULL,
    city TEXT DEFAULT NULL,
    state TEXT DEFAULT NULL,
    birth_date DATE DEFAULT NULL,
    phone TEXT NOT NULL,
    email TEXT DEFAULT null
);
