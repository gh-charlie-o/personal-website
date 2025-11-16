-- Seed: One content creator (Aries Terrón) with two channels and their categories
-- This file is NOT part of Liquibase. Run it manually after Liquibase created the schema.
-- It is idempotent regarding this creator: it removes any previous seed for name = 'Aries Terrón' before inserting again.

START TRANSACTION;

-- Remove previous data for this seed (respecting FKs)
DELETE FROM channel_categories
WHERE channel_id IN (
  SELECT id FROM channels WHERE content_creator_id IN (
    SELECT id FROM content_creators WHERE name = 'Aries Terrón'
  )
);
DELETE FROM channels WHERE content_creator_id IN (SELECT id FROM content_creators WHERE name = 'Aries Terrón');
DELETE FROM content_creators WHERE name = 'Aries Terrón';

-- Insert content creator
INSERT INTO content_creators (
  name,
  description,
  personal_description,
  profile_image_url,
  email,
  website,
  external_id,
  is_active,
  created_at,
  updated_at
) VALUES (
  'Aries Terrón',
  'Licenciado en Nutrición y Nutriólogo Deportivo acreditado por la Federación Mexicana de Nutrición Deportiva.',
  'Canal con un tono humorístico, irónico y que no tiene problemas de ir al choque contra todos los que tiran bolasos en las redes.\nSi ves algún video por ahí con mucha exposición pública tirando que la proteína hace mal o que la creatina hace que se te caiga el pelo,\nen algún momento Aries le cae con todo. Particularmente tomo creatina hace muchos años... y la proteína nunca me hizo mal.',
  'https://yt3.googleusercontent.com/ytc/AIdro_mv50BKvSkbVJOIvuqi8Y13ib8vdbFFHK7oAZbjWvN6OtA=s160-c-k-c0x00ffffff-no-rj',
  NULL,
  NULL,
  'seed-aries-terron-001',
  TRUE,
  CURRENT_TIMESTAMP,
  CURRENT_TIMESTAMP
);
SET @creator_id = LAST_INSERT_ID();

-- Shared channel description
SET @ch_desc = 'Nutriólogo y Entrenador 💪🏼🧠\nMe dedico a crear videos sobre fitness y salud con respaldo científico, con un toque de humor ácido. También me encanta desmentir charlatanes y fake news';

-- Channel 1: YouTube
INSERT INTO channels (
  name,
  url,
  platform,
  description,
  image_url,
  content_creator_id,
  created_at,
  updated_at
) VALUES (
  'Aries Terrón (YouTube)',
  'https://www.youtube.com/@AriesTerron',
  'YOUTUBE',
  @ch_desc,
  'https://yt3.googleusercontent.com/ytc/AIdro_mv50BKvSkbVJOIvuqi8Y13ib8vdbFFHK7oAZbjWvN6OtA=s160-c-k-c0x00ffffff-no-rj',
  @creator_id,
  CURRENT_TIMESTAMP,
  CURRENT_TIMESTAMP
);
SET @channel1_id = LAST_INSERT_ID();
INSERT INTO channel_categories (channel_id, category) VALUES
  (@channel1_id, 'HEALTH'),
  (@channel1_id, 'NUTRITION');

-- Channel 2: Instagram
INSERT INTO channels (
  name,
  url,
  platform,
  description,
  image_url,
  content_creator_id,
  created_at,
  updated_at
) VALUES (
  'Aries Terrón (Instagram)',
  'https://www.instagram.com/aries_terron/',
  'INSTAGRAM',
  @ch_desc,
  'https://yt3.googleusercontent.com/ytc/AIdro_mv50BKvSkbVJOIvuqi8Y13ib8vdbFFHK7oAZbjWvN6OtA=s160-c-k-c0x00ffffff-no-rj',
  @creator_id,
  CURRENT_TIMESTAMP,
  CURRENT_TIMESTAMP
);
SET @channel2_id = LAST_INSERT_ID();
INSERT INTO channel_categories (channel_id, category) VALUES
  (@channel2_id, 'HEALTH'),
  (@channel2_id, 'NUTRITION');

COMMIT;
