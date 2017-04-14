package ru.javawebinar.topjava.repository.jdbc;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.DateTimeUtil;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

@Repository
public class JdbcMealRepositoryImpl implements MealRepository {
    private static final Logger LOG = getLogger(JdbcMealRepositoryImpl.class);

    private static final BeanPropertyRowMapper<Meal> ROW_MAPPER = BeanPropertyRowMapper.newInstance(Meal.class);

    private final JdbcTemplate jdbcTemplate;

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final SimpleJdbcInsert insertMeal;

    @Autowired
    public JdbcMealRepositoryImpl(DataSource dataSource, JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.insertMeal = new SimpleJdbcInsert(dataSource)
                .withTableName("meals")
                .usingGeneratedKeyColumns("id");

        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public Meal save(Meal meal, int userId) {
        //TODO implement!
        return null;
    }

    @Override
    public boolean delete(int id, int userId) {
        //TODO implement!
        List<Meal> meals = jdbcTemplate.query("SELECT * FROM meals WHERE id=? AND user_id=?", ROW_MAPPER, id, userId);
        return false;
    }

    @Override
    public Meal get(int id, int userId) {
        List<Meal> meals = jdbcTemplate.query("SELECT * FROM meals WHERE id=? AND user_id=?", ROW_MAPPER, id, userId);
        return DataAccessUtils.singleResult(meals);
    }

    @Override
    public List<Meal> getAll(int userId) {
        LOG.info("getAll. userId = " + userId);
        List<Meal> meals = jdbcTemplate.query("SELECT * FROM meals WHERE user_id="+userId+" ORDER BY date_time DESC", ROW_MAPPER);
        LOG.debug(Arrays.deepToString(meals.toArray()));
        return meals;
    }

    @Override
    public List<Meal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        String startDateStr = DateTimeUtil.toString(startDate);
        String endDateStr = DateTimeUtil.toString(endDate);
        LOG.debug("getBetween(). startDate = " + startDateStr);
        LOG.debug("getBetween(). endDate = " + endDateStr);
        LOG.debug("getBetween(). userId = " + userId);
        return jdbcTemplate.query(
                "SELECT * FROM meals" +
                        " WHERE date_time >= timestamp '"+ startDateStr +
                        "' AND date_time <= timestamp '"+ endDateStr +
                        "' AND user_id="+ userId +
                        " ORDER BY date_time DESC",
                ROW_MAPPER);
        //LOG.debug(Arrays.deepToString(meals.toArray()));
/*        return jdbcTemplate.query(
                "SELECT * FROM meals " +
                        "WHERE date_time >= timestamp '?' AND date_time <= timestamp '?' " +
                        "AND user_id='?' ORDER BY date_time DESC",
                ROW_MAPPER, startDateStr, endDateStr, userId);*/
        //return meals;
    }
}
