/**
 * 
 */
package jms.poc.cluster.jms.consumer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;

import jms.poc.cluster.jms.model.MessageCount;

/**
 * @author jsantuci
 *
 */
@Stateless
public class MessageCounterBean implements MessageCounter {

//	@Resource(name="CounterDS", mappedName="java:CounterDS")
	@Resource(mappedName="java:CounterDS")
	private DataSource dataSource;

	/**
	 * Default constructor
	 */
	public MessageCounterBean() {
		super();
	}

	/**
	 * @see jms.poc.cluster.jms.consumer.MessageCounter#getTotalCount()
	 */
	@Override
	public long getTotalCount() {
		long count = 0;
		Statement statement = null;
		Connection connection = null;
		try {
			connection = this.dataSource.getConnection();
			statement = connection.createStatement();

			ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) FROM COUNT");
			if (resultSet.next()) {
				count = resultSet.getLong(1);
			}
			resultSet.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (statement != null) {
					statement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return count;
	}

	@Override
	public long getTotalCount(int clientId) {
		long count = 0;
		PreparedStatement ps = null;
		Connection connection = null;
		try {
			connection = this.dataSource.getConnection();
			ps = connection.prepareStatement("SELECT COUNT(*) FROM COUNT WHERE clientId = ?");

			ps.setInt(1, clientId);

			ResultSet resultSet = ps.executeQuery();
			if (resultSet.next()) {
				count = resultSet.getLong(1);
			}
			resultSet.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return count;
	}

	/**
	 * @see jms.poc.cluster.jms.consumer.MessageCounter#add(MessageCount)
	 */
	@Override
	public void add(MessageCount messageCount) {
		PreparedStatement ps = null;
		Connection connection = null;
		try {
			connection = this.dataSource.getConnection();
			ps = connection.prepareStatement("INSERT INTO COUNT (clientId, messageId, repetition, prodId, consId) VALUES (?, ?, ?, ?, ?)");

			ps.setInt(1, messageCount.getClientId());
			ps.setLong(2, messageCount.getMessageId());
			ps.setInt(3, messageCount.getRepetition());
			ps.setString(4, messageCount.getProdId());
			ps.setString(5, messageCount.getConsId());

			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
