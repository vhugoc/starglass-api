CREATE TABLE material (
    id TEXT UNIQUE NOT NULL PRIMARY KEY,
    merchant_id TEXT NOT NULL references merchant(id),
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    is_active BOOLEAN DEFAULT true,
    type TEXT NOT NULL,
    name TEXT NOT NULL,
    color TEXT DEFAULT NULL,
    thickness TEXT DEFAULT NULL,
    value FLOAT NOT NULL
);

CREATE TABLE product (
    id TEXT UNIQUE NOT NULL PRIMARY KEY,
    merchant_id TEXT NOT NULL references merchant(id),
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    is_active BOOLEAN DEFAULT true,
    name TEXT DEFAULT NULL,
    description TEXT NOT NULL
);

CREATE TABLE product_materials (
    id TEXT UNIQUE NOT NULL PRIMARY KEY,
    merchant_id TEXT NOT NULL references merchant(id),
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    is_active BOOLEAN DEFAULT true,
    product_id TEXT NOT NULL references product(id),
    material_id TEXT NOT NULL references material(id),
    quantity INT DEFAULT 1
);
