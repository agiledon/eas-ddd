package xyz.zhangyi.ddd.eas.trainingcontext.gateway.impl.persistence.typehandlers;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import xyz.zhangyi.ddd.eas.trainingcontext.domain.ticket.TicketId;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedTypes(TicketId.class)
@MappedJdbcTypes(JdbcType.VARCHAR)
public class TicketIdTypeHandler extends BaseTypeHandler<TicketId> {
    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, TicketId parameter, JdbcType jdbcType) throws SQLException {
        preparedStatement.setString(i, parameter.value());
    }

    @Override
    public TicketId getNullableResult(ResultSet resultSet, String columnName) throws SQLException {
        return TicketId.from(resultSet.getString(columnName));
    }

    @Override
    public TicketId getNullableResult(ResultSet resultSet, int columnIndex) throws SQLException {
        return TicketId.from(resultSet.getString(columnIndex));
    }

    @Override
    public TicketId getNullableResult(CallableStatement callableStatement, int columnIndex) throws SQLException {
        return TicketId.from(callableStatement.getString(columnIndex));
    }
}