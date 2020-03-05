package xyz.zhangyi.ddd.eas.trainingcontext.acl.adapters.repositories.typehandlers;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import xyz.zhangyi.ddd.eas.trainingcontext.domain.training.TrainingId;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedTypes(TrainingId.class)
@MappedJdbcTypes(JdbcType.VARCHAR)
public class TrainingIdTypeHandler extends BaseTypeHandler<TrainingId> {
    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, TrainingId parameter, JdbcType jdbcType) throws SQLException {
        preparedStatement.setString(i, parameter.value());
    }

    @Override
    public TrainingId getNullableResult(ResultSet resultSet, String columnName) throws SQLException {
        return TrainingId.from(resultSet.getString(columnName));
    }

    @Override
    public TrainingId getNullableResult(ResultSet resultSet, int columnIndex) throws SQLException {
        return TrainingId.from(resultSet.getString(columnIndex));
    }

    @Override
    public TrainingId getNullableResult(CallableStatement callableStatement, int columnIndex) throws SQLException {
        return TrainingId.from(callableStatement.getString(columnIndex));
    }
}