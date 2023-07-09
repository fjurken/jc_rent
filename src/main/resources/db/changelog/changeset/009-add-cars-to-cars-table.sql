insert into cars(
    id,
    created,
    updated,
    status,

    brand,
    model,
    car_type,
    color,
    engine_type,
    engine_capacity,
    engine_power,
    transmission,
    licence_plate
) values (
    0,
    current_timestamp(),
    null,
    'ACTIVE',

    'AUDI',
    'RS6',
    'SPORT',
    'BLACK',
    'PETROL',
    '4.0',
    600,
    'AUTO',
    'AUDIRS6'
), (
    1,
    current_timestamp(),
    null,
    'ACTIVE',

    'BMW',
    'M5',
    'SPORT',
    'BLACK',
    'PETROL',
    '4.4',
    625,
    'AUTO',
    'BMWM5'
), (
    2,
    current_timestamp(),
    null,
    'ACTIVE',

    'MERCEDES_BENZ',
    'GLE COUPE 63 AMG',
    'SPORT',
    'BLACK',
    'PETROL',
    '5.5',
    557,
    'AUTO',
    '63AMG'
);