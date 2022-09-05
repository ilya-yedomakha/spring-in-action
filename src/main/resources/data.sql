delete
from Taco_Order_Tacos;

delete
from Taco_Ingredients;

delete
from Taco;

delete
from Taco_Order;

delete
from Ingredient;

insert into Ingredient (id, name, type)
    values ('FLTO', 'Flour Tortilla', 'WRAP');

insert into Ingredient (id, name, type)
    values ('COTO', 'Corn Tortilla', 'WRAP');

insert into Ingredient (id, name, type)
    values ('GRBF', 'Ground Beef', 'PROTEIN');

insert into Ingredient (id, name, type)
    values ('CARN', 'Carnitas', 'PROTEIN');

insert into Ingredient (id, name, type)
    values ('TMTO', 'Diced Tomatoes', 'VEGGIES');

insert into Ingredient (id, name, type)
    values ('LETC', 'Lettuce', 'VEGGIES');

insert into Ingredient (id, name, type)
    values ('CHED', 'Cheddar', 'CHEESE');

insert into Ingredient (id, name, type)
    values ('JACK', 'Monterrey Jack', 'CHEESE');

insert into Ingredient (id, name, type)
    values ('SLSA', 'Salsa', 'SAUCE');

insert into Ingredient (id, name, type)
    values ('SRCR', 'Sour Cream', 'SAUCE');

-- insert into Users (username, password)
-- values ('username','9e7e3a73a40871d4b489adb746c31ace280d28206dded9665bac40eabfe6ffdc32a8c5c416b5878f');
--
-- insert into UserAuthorities (username, authority)
-- values ('username', 'USER');
