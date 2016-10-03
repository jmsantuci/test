/**
 * 
 */
package jms.test.jndi;

import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * @author jsantuci
 *
 */
public class CreateAliases {


	private static InitialContext initialContext;
	private static List<Alias> aliases = new ArrayList<Alias>();
	private static List<Tree> trees = new ArrayList<Tree>();
	
	static {
		try {
			initialContext = new InitialContext();
			
			trees.add(new Tree(initialContext, "CPqD-ITControl/6/9/0/0", "WebApplication/6/9/0/0/br/com/cpqd/framework/satellite/access"));
			trees.add(new Tree(initialContext, "CPqD-ITControl/6/9/0/0/WebApplication/6/9/0/0/br/com/cpqd/framework", "docmanager/business"));
			trees.add(new Tree(initialContext, "CPqD-ITControl/6/9/0/0/WebApplication/6/9/0/0/br/com/cpqd/framework/satellite", "business/logicalkey"));
			trees.add(new Tree(initialContext, "CPqD-ITControl/6/9/0/0/WebApplication/6/9/0/0/br/com/cpqd/framework/satellite/business", "meta"));
			trees.add(new Tree(initialContext, "CPqD-ITControl/6/9/0/0/WebApplication/6/9/0/0/br/com/cpqd/framework/satellite/business", "spatial"));
			trees.add(new Tree(initialContext, "CPqD-ITControl/6/9/0/0/WebApplication/6/9/0/0/br/com/cpqd/framework/satellite", "transaction"));
			trees.add(new Tree(initialContext, "CPqD-ITControl/6/9/0/0/WebApplication/6/9/0/0/br/com/cpqd", "faces/deskmap/schematic/business"));
			trees.add(new Tree(initialContext, "CPqD-ITControl/6/9/0/0/WebApplication/6/9/0/0/br/com/cpqd", "geo/renderer/request/server"));
			trees.add(new Tree(initialContext, "CPqD-ITControl/6/9/0/0/WebApplication/6/9/0/0/br/com/cpqd/geo", "query/business"));
			
			trees.add(new Tree(initialContext, "CPqD-ITControl/6/9/0/0", "WebMapApplication/6/9/0/0/br/com/cpqd/framework/satellite/access"));
			trees.add(new Tree(initialContext, "CPqD-ITControl/6/9/0/0/WebMapApplication/6/9/0/0/br/com/cpqd/framework", "docmanager/business"));
			trees.add(new Tree(initialContext, "CPqD-ITControl/6/9/0/0/WebMapApplication/6/9/0/0/br/com/cpqd/framework/satellite", "business/logicalkey"));
			trees.add(new Tree(initialContext, "CPqD-ITControl/6/9/0/0/WebMapApplication/6/9/0/0/br/com/cpqd/framework/satellite/business", "meta"));
			trees.add(new Tree(initialContext, "CPqD-ITControl/6/9/0/0/WebMapApplication/6/9/0/0/br/com/cpqd/framework/satellite/business", "spatial"));
			trees.add(new Tree(initialContext, "CPqD-ITControl/6/9/0/0/WebMapApplication/6/9/0/0/br/com/cpqd/framework/satellite", "transaction"));
			trees.add(new Tree(initialContext, "CPqD-ITControl/6/9/0/0/WebMapApplication/6/9/0/0/br/com/cpqd", "faces/deskmap/schematic/business"));
			trees.add(new Tree(initialContext, "CPqD-ITControl/6/9/0/0/WebMapApplication/6/9/0/0/br/com/cpqd", "geo/renderer/request/server"));
			trees.add(new Tree(initialContext, "CPqD-ITControl/6/9/0/0/WebMapApplication/6/9/0/0/br/com/cpqd/geo", "query/business"));

			trees.add(new Tree(initialContext, "CPqD-ITControl/6/9/0/0", "WebReportApplication/6/9/0/0/br/com/cpqd/framework/satellite/access"));
			trees.add(new Tree(initialContext, "CPqD-ITControl/6/9/0/0/WebReportApplication/6/9/0/0/br/com/cpqd/framework", "docmanager/business"));
			trees.add(new Tree(initialContext, "CPqD-ITControl/6/9/0/0/WebReportApplication/6/9/0/0/br/com/cpqd/framework/satellite", "business/logicalkey"));
			trees.add(new Tree(initialContext, "CPqD-ITControl/6/9/0/0/WebReportApplication/6/9/0/0/br/com/cpqd/framework/satellite/business", "meta"));
			trees.add(new Tree(initialContext, "CPqD-ITControl/6/9/0/0/WebReportApplication/6/9/0/0/br/com/cpqd/framework/satellite/business", "spatial"));
			trees.add(new Tree(initialContext, "CPqD-ITControl/6/9/0/0/WebReportApplication/6/9/0/0/br/com/cpqd/framework/satellite", "transaction"));
			trees.add(new Tree(initialContext, "CPqD-ITControl/6/9/0/0/WebReportApplication/6/9/0/0/br/com/cpqd", "faces/deskmap/schematic/business"));
			trees.add(new Tree(initialContext, "CPqD-ITControl/6/9/0/0/WebReportApplication/6/9/0/0/br/com/cpqd", "geo/renderer/request/server"));
			trees.add(new Tree(initialContext, "CPqD-ITControl/6/9/0/0/WebReportApplication/6/9/0/0/br/com/cpqd/geo", "query/business"));

			aliases.add(new Alias(initialContext, "CPqD-ITControl/6/9/0/0/WebApplication/6/9/0/0/br/com/cpqd/faces/deskmap/schematic/business/PlanDataObjectRemotePublicHttp", "CPqD-ITControl/6/9/0/0/WebDeskMapReport/6/9/0/0/br/com/cpqd/faces/deskmap/schematic/business/PlanDataObjectRemotePublicHttp"));
			aliases.add(new Alias(initialContext, "CPqD-ITControl/6/9/0/0/WebApplication/6/9/0/0/br/com/cpqd/framework/satellite/access/AccessObjectRemotePublicHttp", "CPqD-ITControl/6/9/0/0/WebDeskMapReport/6/9/0/0/br/com/cpqd/framework/satellite/access/AccessObjectRemotePublicHttp"));
			aliases.add(new Alias(initialContext, "CPqD-ITControl/6/9/0/0/WebApplication/6/9/0/0/br/com/cpqd/geo/renderer/request/server/RendererRequestManagerPublicHttp", "CPqD-ITControl/6/9/0/0/WebDeskMapReport/6/9/0/0/br/com/cpqd/geo/renderer/request/server/RendererRequestManagerPublicHttp"));
			aliases.add(new Alias(initialContext, "CPqD-ITControl/6/9/0/0/WebApplication/6/9/0/0/br/com/cpqd/faces/deskmap/schematic/business/PlanDataObjectRemote", "CPqD-ITControl/6/9/0/0/WebDeskMapReport/6/9/0/0/br/com/cpqd/faces/deskmap/schematic/business/PlanDataObjectRemote"));
			aliases.add(new Alias(initialContext, "CPqD-ITControl/6/9/0/0/WebApplication/6/9/0/0/br/com/cpqd/framework/satellite/access/AccessObjectRemote", "CPqD-ITControl/6/9/0/0/WebDeskMapReport/6/9/0/0/br/com/cpqd/framework/satellite/access/AccessObjectRemote"));
			aliases.add(new Alias(initialContext, "CPqD-ITControl/6/9/0/0/WebApplication/6/9/0/0/br/com/cpqd/framework/docmanager/business/DocumentContentsObjectBeanRemote", "CPqD-ITControl/6/9/0/0/WebDeskMapReport/6/9/0/0/br/com/cpqd/framework/docmanager/business/DocumentContentsObjectBeanRemote"));
			aliases.add(new Alias(initialContext, "CPqD-ITControl/6/9/0/0/WebApplication/6/9/0/0/br/com/cpqd/framework/docmanager/business/DocumentContentsObjectBeanLocal", "CPqD-ITControl/6/9/0/0/WebDeskMapReport/6/9/0/0/br/com/cpqd/framework/docmanager/business/DocumentContentsObjectBeanLocal"));
			aliases.add(new Alias(initialContext, "CPqD-ITControl/6/9/0/0/WebApplication/6/9/0/0/br/com/cpqd/framework/satellite/business/UniqueIDGeneratorRemote", "CPqD-ITControl/6/9/0/0/WebDeskMapReport/6/9/0/0/br/com/cpqd/framework/satellite/business/UniqueIDGeneratorRemote"));
			aliases.add(new Alias(initialContext, "CPqD-ITControl/6/9/0/0/WebApplication/6/9/0/0/br/com/cpqd/framework/satellite/business/logicalkey/ConstraintBusinessObjectLocal", "CPqD-ITControl/6/9/0/0/WebDeskMapReport/6/9/0/0/br/com/cpqd/framework/satellite/business/logicalkey/ConstraintBusinessObjectLocal"));
			aliases.add(new Alias(initialContext, "CPqD-ITControl/6/9/0/0/WebApplication/6/9/0/0/br/com/cpqd/framework/satellite/business/meta/FilterMetaBusinessBeanLocal", "CPqD-ITControl/6/9/0/0/WebDeskMapReport/6/9/0/0/br/com/cpqd/framework/satellite/business/meta/FilterMetaBusinessBeanLocal"));
			aliases.add(new Alias(initialContext, "CPqD-ITControl/6/9/0/0/WebApplication/6/9/0/0/br/com/cpqd/framework/satellite/business/meta/MetaBusinessObjectRemote", "CPqD-ITControl/6/9/0/0/WebDeskMapReport/6/9/0/0/br/com/cpqd/framework/satellite/business/meta/MetaBusinessObjectRemote"));
			aliases.add(new Alias(initialContext, "CPqD-ITControl/6/9/0/0/WebApplication/6/9/0/0/br/com/cpqd/framework/satellite/business/meta/MetaBusinessObjectLocal", "CPqD-ITControl/6/9/0/0/WebDeskMapReport/6/9/0/0/br/com/cpqd/framework/satellite/business/meta/MetaBusinessObjectLocal"));
			aliases.add(new Alias(initialContext, "CPqD-ITControl/6/9/0/0/WebApplication/6/9/0/0/br/com/cpqd/framework/satellite/business/meta/MetaQueryObjectRemote", "CPqD-ITControl/6/9/0/0/WebDeskMapReport/6/9/0/0/br/com/cpqd/framework/satellite/business/meta/MetaQueryObjectRemote"));
			aliases.add(new Alias(initialContext, "CPqD-ITControl/6/9/0/0/WebApplication/6/9/0/0/br/com/cpqd/framework/satellite/business/meta/MetaQueryObjectLocal", "CPqD-ITControl/6/9/0/0/WebDeskMapReport/6/9/0/0/br/com/cpqd/framework/satellite/business/meta/MetaQueryObjectLocal"));
			aliases.add(new Alias(initialContext, "CPqD-ITControl/6/9/0/0/WebApplication/6/9/0/0/br/com/cpqd/framework/satellite/business/spatial/SpatialBusinessObjectRemote", "CPqD-ITControl/6/9/0/0/WebDeskMapReport/6/9/0/0/br/com/cpqd/framework/satellite/business/spatial/SpatialBusinessObjectRemote"));
			aliases.add(new Alias(initialContext, "CPqD-ITControl/6/9/0/0/WebApplication/6/9/0/0/br/com/cpqd/framework/satellite/business/spatial/SpatialBusinessObjectLocal", "CPqD-ITControl/6/9/0/0/WebDeskMapReport/6/9/0/0/br/com/cpqd/framework/satellite/business/spatial/SpatialBusinessObjectLocal"));
			aliases.add(new Alias(initialContext, "CPqD-ITControl/6/9/0/0/WebApplication/6/9/0/0/br/com/cpqd/framework/satellite/business/spatial/SpatialFilterObjectRemote", "CPqD-ITControl/6/9/0/0/WebDeskMapReport/6/9/0/0/br/com/cpqd/framework/satellite/business/spatial/SpatialFilterObjectRemote"));
			aliases.add(new Alias(initialContext, "CPqD-ITControl/6/9/0/0/WebApplication/6/9/0/0/br/com/cpqd/framework/satellite/business/spatial/SpatialFilterObjectLocal", "CPqD-ITControl/6/9/0/0/WebDeskMapReport/6/9/0/0/br/com/cpqd/framework/satellite/business/spatial/SpatialFilterObjectLocal"));
			aliases.add(new Alias(initialContext, "CPqD-ITControl/6/9/0/0/WebApplication/6/9/0/0/br/com/cpqd/framework/satellite/business/spatial/SpatialServicesObjectRemote", "CPqD-ITControl/6/9/0/0/WebDeskMapReport/6/9/0/0/br/com/cpqd/framework/satellite/business/spatial/SpatialServicesObjectRemote"));
			aliases.add(new Alias(initialContext, "CPqD-ITControl/6/9/0/0/WebApplication/6/9/0/0/br/com/cpqd/framework/satellite/business/spatial/SpatialServicesObjectLocal", "CPqD-ITControl/6/9/0/0/WebDeskMapReport/6/9/0/0/br/com/cpqd/framework/satellite/business/spatial/SpatialServicesObjectLocal"));
			aliases.add(new Alias(initialContext, "CPqD-ITControl/6/9/0/0/WebApplication/6/9/0/0/br/com/cpqd/framework/satellite/transaction/NonTransactionObjectLocal", "CPqD-ITControl/6/9/0/0/WebDeskMapReport/6/9/0/0/br/com/cpqd/framework/satellite/transaction/NonTransactionObjectLocal"));
			aliases.add(new Alias(initialContext, "CPqD-ITControl/6/9/0/0/WebApplication/6/9/0/0/br/com/cpqd/framework/satellite/transaction/TransactionControllerRemote", "CPqD-ITControl/6/9/0/0/WebDeskMapReport/6/9/0/0/br/com/cpqd/framework/satellite/transaction/TransactionControllerRemote"));
			aliases.add(new Alias(initialContext, "CPqD-ITControl/6/9/0/0/WebApplication/6/9/0/0/br/com/cpqd/framework/satellite/transaction/TransactionControllerLocal", "CPqD-ITControl/6/9/0/0/WebDeskMapReport/6/9/0/0/br/com/cpqd/framework/satellite/transaction/TransactionControllerLocal"));
			aliases.add(new Alias(initialContext, "CPqD-ITControl/6/9/0/0/WebApplication/6/9/0/0/br/com/cpqd/framework/satellite/transaction/TransactionObjectLocal", "CPqD-ITControl/6/9/0/0/WebDeskMapReport/6/9/0/0/br/com/cpqd/framework/satellite/transaction/TransactionObjectLocal"));
			aliases.add(new Alias(initialContext, "CPqD-ITControl/6/9/0/0/WebApplication/6/9/0/0/br/com/cpqd/geo/query/business/QueryObjectRemote", "CPqD-ITControl/6/9/0/0/WebDeskMapReport/6/9/0/0/br/com/cpqd/geo/query/business/QueryObjectRemote"));
			aliases.add(new Alias(initialContext, "CPqD-ITControl/6/9/0/0/WebApplication/6/9/0/0/br/com/cpqd/geo/query/business/QueryObjectLocal", "CPqD-ITControl/6/9/0/0/WebDeskMapReport/6/9/0/0/br/com/cpqd/geo/query/business/QueryObjectLocal"));
			aliases.add(new Alias(initialContext, "CPqD-ITControl/6/9/0/0/WebApplication/6/9/0/0/br/com/cpqd/geo/renderer/request/server/RendererRequestManager", "CPqD-ITControl/6/9/0/0/WebDeskMapReport/6/9/0/0/br/com/cpqd/geo/renderer/request/server/RendererRequestManager"));

			aliases.add(new Alias(initialContext, "CPqD-ITControl/6/9/0/0/WebMapApplication/6/9/0/0/br/com/cpqd/faces/deskmap/schematic/business/PlanDataObjectRemotePublicHttp", "CPqD-ITControl/6/9/0/0/WebDeskMapReport/6/9/0/0/br/com/cpqd/faces/deskmap/schematic/business/PlanDataObjectRemotePublicHttp"));
			aliases.add(new Alias(initialContext, "CPqD-ITControl/6/9/0/0/WebMapApplication/6/9/0/0/br/com/cpqd/framework/satellite/access/AccessObjectRemotePublicHttp", "CPqD-ITControl/6/9/0/0/WebDeskMapReport/6/9/0/0/br/com/cpqd/framework/satellite/access/AccessObjectRemotePublicHttp"));
			aliases.add(new Alias(initialContext, "CPqD-ITControl/6/9/0/0/WebMapApplication/6/9/0/0/br/com/cpqd/geo/renderer/request/server/RendererRequestManagerPublicHttp", "CPqD-ITControl/6/9/0/0/WebDeskMapReport/6/9/0/0/br/com/cpqd/geo/renderer/request/server/RendererRequestManagerPublicHttp"));
			aliases.add(new Alias(initialContext, "CPqD-ITControl/6/9/0/0/WebMapApplication/6/9/0/0/br/com/cpqd/faces/deskmap/schematic/business/PlanDataObjectRemote", "CPqD-ITControl/6/9/0/0/WebDeskMapReport/6/9/0/0/br/com/cpqd/faces/deskmap/schematic/business/PlanDataObjectRemote"));
			aliases.add(new Alias(initialContext, "CPqD-ITControl/6/9/0/0/WebMapApplication/6/9/0/0/br/com/cpqd/framework/satellite/access/AccessObjectRemote", "CPqD-ITControl/6/9/0/0/WebDeskMapReport/6/9/0/0/br/com/cpqd/framework/satellite/access/AccessObjectRemote"));
			aliases.add(new Alias(initialContext, "CPqD-ITControl/6/9/0/0/WebMapApplication/6/9/0/0/br/com/cpqd/framework/docmanager/business/DocumentContentsObjectBeanRemote", "CPqD-ITControl/6/9/0/0/WebDeskMapReport/6/9/0/0/br/com/cpqd/framework/docmanager/business/DocumentContentsObjectBeanRemote"));
			aliases.add(new Alias(initialContext, "CPqD-ITControl/6/9/0/0/WebMapApplication/6/9/0/0/br/com/cpqd/framework/docmanager/business/DocumentContentsObjectBeanLocal", "CPqD-ITControl/6/9/0/0/WebDeskMapReport/6/9/0/0/br/com/cpqd/framework/docmanager/business/DocumentContentsObjectBeanLocal"));
			aliases.add(new Alias(initialContext, "CPqD-ITControl/6/9/0/0/WebMapApplication/6/9/0/0/br/com/cpqd/framework/satellite/business/UniqueIDGeneratorRemote", "CPqD-ITControl/6/9/0/0/WebDeskMapReport/6/9/0/0/br/com/cpqd/framework/satellite/business/UniqueIDGeneratorRemote"));
			aliases.add(new Alias(initialContext, "CPqD-ITControl/6/9/0/0/WebMapApplication/6/9/0/0/br/com/cpqd/framework/satellite/business/logicalkey/ConstraintBusinessObjectLocal", "CPqD-ITControl/6/9/0/0/WebDeskMapReport/6/9/0/0/br/com/cpqd/framework/satellite/business/logicalkey/ConstraintBusinessObjectLocal"));
			aliases.add(new Alias(initialContext, "CPqD-ITControl/6/9/0/0/WebMapApplication/6/9/0/0/br/com/cpqd/framework/satellite/business/meta/FilterMetaBusinessBeanLocal", "CPqD-ITControl/6/9/0/0/WebDeskMapReport/6/9/0/0/br/com/cpqd/framework/satellite/business/meta/FilterMetaBusinessBeanLocal"));
			aliases.add(new Alias(initialContext, "CPqD-ITControl/6/9/0/0/WebMapApplication/6/9/0/0/br/com/cpqd/framework/satellite/business/meta/MetaBusinessObjectRemote", "CPqD-ITControl/6/9/0/0/WebDeskMapReport/6/9/0/0/br/com/cpqd/framework/satellite/business/meta/MetaBusinessObjectRemote"));
			aliases.add(new Alias(initialContext, "CPqD-ITControl/6/9/0/0/WebMapApplication/6/9/0/0/br/com/cpqd/framework/satellite/business/meta/MetaBusinessObjectLocal", "CPqD-ITControl/6/9/0/0/WebDeskMapReport/6/9/0/0/br/com/cpqd/framework/satellite/business/meta/MetaBusinessObjectLocal"));
			aliases.add(new Alias(initialContext, "CPqD-ITControl/6/9/0/0/WebMapApplication/6/9/0/0/br/com/cpqd/framework/satellite/business/meta/MetaQueryObjectRemote", "CPqD-ITControl/6/9/0/0/WebDeskMapReport/6/9/0/0/br/com/cpqd/framework/satellite/business/meta/MetaQueryObjectRemote"));
			aliases.add(new Alias(initialContext, "CPqD-ITControl/6/9/0/0/WebMapApplication/6/9/0/0/br/com/cpqd/framework/satellite/business/meta/MetaQueryObjectLocal", "CPqD-ITControl/6/9/0/0/WebDeskMapReport/6/9/0/0/br/com/cpqd/framework/satellite/business/meta/MetaQueryObjectLocal"));
			aliases.add(new Alias(initialContext, "CPqD-ITControl/6/9/0/0/WebMapApplication/6/9/0/0/br/com/cpqd/framework/satellite/business/spatial/SpatialBusinessObjectRemote", "CPqD-ITControl/6/9/0/0/WebDeskMapReport/6/9/0/0/br/com/cpqd/framework/satellite/business/spatial/SpatialBusinessObjectRemote"));
			aliases.add(new Alias(initialContext, "CPqD-ITControl/6/9/0/0/WebMapApplication/6/9/0/0/br/com/cpqd/framework/satellite/business/spatial/SpatialBusinessObjectLocal", "CPqD-ITControl/6/9/0/0/WebDeskMapReport/6/9/0/0/br/com/cpqd/framework/satellite/business/spatial/SpatialBusinessObjectLocal"));
			aliases.add(new Alias(initialContext, "CPqD-ITControl/6/9/0/0/WebMapApplication/6/9/0/0/br/com/cpqd/framework/satellite/business/spatial/SpatialFilterObjectRemote", "CPqD-ITControl/6/9/0/0/WebDeskMapReport/6/9/0/0/br/com/cpqd/framework/satellite/business/spatial/SpatialFilterObjectRemote"));
			aliases.add(new Alias(initialContext, "CPqD-ITControl/6/9/0/0/WebMapApplication/6/9/0/0/br/com/cpqd/framework/satellite/business/spatial/SpatialFilterObjectLocal", "CPqD-ITControl/6/9/0/0/WebDeskMapReport/6/9/0/0/br/com/cpqd/framework/satellite/business/spatial/SpatialFilterObjectLocal"));
			aliases.add(new Alias(initialContext, "CPqD-ITControl/6/9/0/0/WebMapApplication/6/9/0/0/br/com/cpqd/framework/satellite/business/spatial/SpatialServicesObjectRemote", "CPqD-ITControl/6/9/0/0/WebDeskMapReport/6/9/0/0/br/com/cpqd/framework/satellite/business/spatial/SpatialServicesObjectRemote"));
			aliases.add(new Alias(initialContext, "CPqD-ITControl/6/9/0/0/WebMapApplication/6/9/0/0/br/com/cpqd/framework/satellite/business/spatial/SpatialServicesObjectLocal", "CPqD-ITControl/6/9/0/0/WebDeskMapReport/6/9/0/0/br/com/cpqd/framework/satellite/business/spatial/SpatialServicesObjectLocal"));
			aliases.add(new Alias(initialContext, "CPqD-ITControl/6/9/0/0/WebMapApplication/6/9/0/0/br/com/cpqd/framework/satellite/transaction/NonTransactionObjectLocal", "CPqD-ITControl/6/9/0/0/WebDeskMapReport/6/9/0/0/br/com/cpqd/framework/satellite/transaction/NonTransactionObjectLocal"));
			aliases.add(new Alias(initialContext, "CPqD-ITControl/6/9/0/0/WebMapApplication/6/9/0/0/br/com/cpqd/framework/satellite/transaction/TransactionControllerRemote", "CPqD-ITControl/6/9/0/0/WebDeskMapReport/6/9/0/0/br/com/cpqd/framework/satellite/transaction/TransactionControllerRemote"));
			aliases.add(new Alias(initialContext, "CPqD-ITControl/6/9/0/0/WebMapApplication/6/9/0/0/br/com/cpqd/framework/satellite/transaction/TransactionControllerLocal", "CPqD-ITControl/6/9/0/0/WebDeskMapReport/6/9/0/0/br/com/cpqd/framework/satellite/transaction/TransactionControllerLocal"));
			aliases.add(new Alias(initialContext, "CPqD-ITControl/6/9/0/0/WebMapApplication/6/9/0/0/br/com/cpqd/framework/satellite/transaction/TransactionObjectLocal", "CPqD-ITControl/6/9/0/0/WebDeskMapReport/6/9/0/0/br/com/cpqd/framework/satellite/transaction/TransactionObjectLocal"));
			aliases.add(new Alias(initialContext, "CPqD-ITControl/6/9/0/0/WebMapApplication/6/9/0/0/br/com/cpqd/geo/query/business/QueryObjectRemote", "CPqD-ITControl/6/9/0/0/WebDeskMapReport/6/9/0/0/br/com/cpqd/geo/query/business/QueryObjectRemote"));
			aliases.add(new Alias(initialContext, "CPqD-ITControl/6/9/0/0/WebMapApplication/6/9/0/0/br/com/cpqd/geo/query/business/QueryObjectLocal", "CPqD-ITControl/6/9/0/0/WebDeskMapReport/6/9/0/0/br/com/cpqd/geo/query/business/QueryObjectLocal"));
			aliases.add(new Alias(initialContext, "CPqD-ITControl/6/9/0/0/WebMapApplication/6/9/0/0/br/com/cpqd/geo/renderer/request/server/RendererRequestManager", "CPqD-ITControl/6/9/0/0/WebDeskMapReport/6/9/0/0/br/com/cpqd/geo/renderer/request/server/RendererRequestManager"));

			aliases.add(new Alias(initialContext, "CPqD-ITControl/6/9/0/0/WebReportApplication/6/9/0/0/br/com/cpqd/faces/deskmap/schematic/business/PlanDataObjectRemotePublicHttp", "CPqD-ITControl/6/9/0/0/WebDeskMapReport/6/9/0/0/br/com/cpqd/faces/deskmap/schematic/business/PlanDataObjectRemotePublicHttp"));
			aliases.add(new Alias(initialContext, "CPqD-ITControl/6/9/0/0/WebReportApplication/6/9/0/0/br/com/cpqd/framework/satellite/access/AccessObjectRemotePublicHttp", "CPqD-ITControl/6/9/0/0/WebDeskMapReport/6/9/0/0/br/com/cpqd/framework/satellite/access/AccessObjectRemotePublicHttp"));
			aliases.add(new Alias(initialContext, "CPqD-ITControl/6/9/0/0/WebReportApplication/6/9/0/0/br/com/cpqd/geo/renderer/request/server/RendererRequestManagerPublicHttp", "CPqD-ITControl/6/9/0/0/WebDeskMapReport/6/9/0/0/br/com/cpqd/geo/renderer/request/server/RendererRequestManagerPublicHttp"));
			aliases.add(new Alias(initialContext, "CPqD-ITControl/6/9/0/0/WebReportApplication/6/9/0/0/br/com/cpqd/faces/deskmap/schematic/business/PlanDataObjectRemote", "CPqD-ITControl/6/9/0/0/WebDeskMapReport/6/9/0/0/br/com/cpqd/faces/deskmap/schematic/business/PlanDataObjectRemote"));
			aliases.add(new Alias(initialContext, "CPqD-ITControl/6/9/0/0/WebReportApplication/6/9/0/0/br/com/cpqd/framework/satellite/access/AccessObjectRemote", "CPqD-ITControl/6/9/0/0/WebDeskMapReport/6/9/0/0/br/com/cpqd/framework/satellite/access/AccessObjectRemote"));
			aliases.add(new Alias(initialContext, "CPqD-ITControl/6/9/0/0/WebReportApplication/6/9/0/0/br/com/cpqd/framework/docmanager/business/DocumentContentsObjectBeanRemote", "CPqD-ITControl/6/9/0/0/WebDeskMapReport/6/9/0/0/br/com/cpqd/framework/docmanager/business/DocumentContentsObjectBeanRemote"));
			aliases.add(new Alias(initialContext, "CPqD-ITControl/6/9/0/0/WebReportApplication/6/9/0/0/br/com/cpqd/framework/docmanager/business/DocumentContentsObjectBeanLocal", "CPqD-ITControl/6/9/0/0/WebDeskMapReport/6/9/0/0/br/com/cpqd/framework/docmanager/business/DocumentContentsObjectBeanLocal"));
			aliases.add(new Alias(initialContext, "CPqD-ITControl/6/9/0/0/WebReportApplication/6/9/0/0/br/com/cpqd/framework/satellite/business/UniqueIDGeneratorRemote", "CPqD-ITControl/6/9/0/0/WebDeskMapReport/6/9/0/0/br/com/cpqd/framework/satellite/business/UniqueIDGeneratorRemote"));
			aliases.add(new Alias(initialContext, "CPqD-ITControl/6/9/0/0/WebReportApplication/6/9/0/0/br/com/cpqd/framework/satellite/business/logicalkey/ConstraintBusinessObjectLocal", "CPqD-ITControl/6/9/0/0/WebDeskMapReport/6/9/0/0/br/com/cpqd/framework/satellite/business/logicalkey/ConstraintBusinessObjectLocal"));
			aliases.add(new Alias(initialContext, "CPqD-ITControl/6/9/0/0/WebReportApplication/6/9/0/0/br/com/cpqd/framework/satellite/business/meta/FilterMetaBusinessBeanLocal", "CPqD-ITControl/6/9/0/0/WebDeskMapReport/6/9/0/0/br/com/cpqd/framework/satellite/business/meta/FilterMetaBusinessBeanLocal"));
			aliases.add(new Alias(initialContext, "CPqD-ITControl/6/9/0/0/WebReportApplication/6/9/0/0/br/com/cpqd/framework/satellite/business/meta/MetaBusinessObjectRemote", "CPqD-ITControl/6/9/0/0/WebDeskMapReport/6/9/0/0/br/com/cpqd/framework/satellite/business/meta/MetaBusinessObjectRemote"));
			aliases.add(new Alias(initialContext, "CPqD-ITControl/6/9/0/0/WebReportApplication/6/9/0/0/br/com/cpqd/framework/satellite/business/meta/MetaBusinessObjectLocal", "CPqD-ITControl/6/9/0/0/WebDeskMapReport/6/9/0/0/br/com/cpqd/framework/satellite/business/meta/MetaBusinessObjectLocal"));
			aliases.add(new Alias(initialContext, "CPqD-ITControl/6/9/0/0/WebReportApplication/6/9/0/0/br/com/cpqd/framework/satellite/business/meta/MetaQueryObjectRemote", "CPqD-ITControl/6/9/0/0/WebDeskMapReport/6/9/0/0/br/com/cpqd/framework/satellite/business/meta/MetaQueryObjectRemote"));
			aliases.add(new Alias(initialContext, "CPqD-ITControl/6/9/0/0/WebReportApplication/6/9/0/0/br/com/cpqd/framework/satellite/business/meta/MetaQueryObjectLocal", "CPqD-ITControl/6/9/0/0/WebDeskMapReport/6/9/0/0/br/com/cpqd/framework/satellite/business/meta/MetaQueryObjectLocal"));
			aliases.add(new Alias(initialContext, "CPqD-ITControl/6/9/0/0/WebReportApplication/6/9/0/0/br/com/cpqd/framework/satellite/business/spatial/SpatialBusinessObjectRemote", "CPqD-ITControl/6/9/0/0/WebDeskMapReport/6/9/0/0/br/com/cpqd/framework/satellite/business/spatial/SpatialBusinessObjectRemote"));
			aliases.add(new Alias(initialContext, "CPqD-ITControl/6/9/0/0/WebReportApplication/6/9/0/0/br/com/cpqd/framework/satellite/business/spatial/SpatialBusinessObjectLocal", "CPqD-ITControl/6/9/0/0/WebDeskMapReport/6/9/0/0/br/com/cpqd/framework/satellite/business/spatial/SpatialBusinessObjectLocal"));
			aliases.add(new Alias(initialContext, "CPqD-ITControl/6/9/0/0/WebReportApplication/6/9/0/0/br/com/cpqd/framework/satellite/business/spatial/SpatialFilterObjectRemote", "CPqD-ITControl/6/9/0/0/WebDeskMapReport/6/9/0/0/br/com/cpqd/framework/satellite/business/spatial/SpatialFilterObjectRemote"));
			aliases.add(new Alias(initialContext, "CPqD-ITControl/6/9/0/0/WebReportApplication/6/9/0/0/br/com/cpqd/framework/satellite/business/spatial/SpatialFilterObjectLocal", "CPqD-ITControl/6/9/0/0/WebDeskMapReport/6/9/0/0/br/com/cpqd/framework/satellite/business/spatial/SpatialFilterObjectLocal"));
			aliases.add(new Alias(initialContext, "CPqD-ITControl/6/9/0/0/WebReportApplication/6/9/0/0/br/com/cpqd/framework/satellite/business/spatial/SpatialServicesObjectRemote", "CPqD-ITControl/6/9/0/0/WebDeskMapReport/6/9/0/0/br/com/cpqd/framework/satellite/business/spatial/SpatialServicesObjectRemote"));
			aliases.add(new Alias(initialContext, "CPqD-ITControl/6/9/0/0/WebReportApplication/6/9/0/0/br/com/cpqd/framework/satellite/business/spatial/SpatialServicesObjectLocal", "CPqD-ITControl/6/9/0/0/WebDeskMapReport/6/9/0/0/br/com/cpqd/framework/satellite/business/spatial/SpatialServicesObjectLocal"));
			aliases.add(new Alias(initialContext, "CPqD-ITControl/6/9/0/0/WebReportApplication/6/9/0/0/br/com/cpqd/framework/satellite/transaction/NonTransactionObjectLocal", "CPqD-ITControl/6/9/0/0/WebDeskMapReport/6/9/0/0/br/com/cpqd/framework/satellite/transaction/NonTransactionObjectLocal"));
			aliases.add(new Alias(initialContext, "CPqD-ITControl/6/9/0/0/WebReportApplication/6/9/0/0/br/com/cpqd/framework/satellite/transaction/TransactionControllerRemote", "CPqD-ITControl/6/9/0/0/WebDeskMapReport/6/9/0/0/br/com/cpqd/framework/satellite/transaction/TransactionControllerRemote"));
			aliases.add(new Alias(initialContext, "CPqD-ITControl/6/9/0/0/WebReportApplication/6/9/0/0/br/com/cpqd/framework/satellite/transaction/TransactionControllerLocal", "CPqD-ITControl/6/9/0/0/WebDeskMapReport/6/9/0/0/br/com/cpqd/framework/satellite/transaction/TransactionControllerLocal"));
			aliases.add(new Alias(initialContext, "CPqD-ITControl/6/9/0/0/WebReportApplication/6/9/0/0/br/com/cpqd/framework/satellite/transaction/TransactionObjectLocal", "CPqD-ITControl/6/9/0/0/WebDeskMapReport/6/9/0/0/br/com/cpqd/framework/satellite/transaction/TransactionObjectLocal"));
			aliases.add(new Alias(initialContext, "CPqD-ITControl/6/9/0/0/WebReportApplication/6/9/0/0/br/com/cpqd/geo/query/business/QueryObjectRemote", "CPqD-ITControl/6/9/0/0/WebDeskMapReport/6/9/0/0/br/com/cpqd/geo/query/business/QueryObjectRemote"));
			aliases.add(new Alias(initialContext, "CPqD-ITControl/6/9/0/0/WebReportApplication/6/9/0/0/br/com/cpqd/geo/query/business/QueryObjectLocal", "CPqD-ITControl/6/9/0/0/WebDeskMapReport/6/9/0/0/br/com/cpqd/geo/query/business/QueryObjectLocal"));
			aliases.add(new Alias(initialContext, "CPqD-ITControl/6/9/0/0/WebReportApplication/6/9/0/0/br/com/cpqd/geo/renderer/request/server/RendererRequestManager", "CPqD-ITControl/6/9/0/0/WebDeskMapReport/6/9/0/0/br/com/cpqd/geo/renderer/request/server/RendererRequestManager"));
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Default constructor.
	 */
	public CreateAliases() {
		super();
	}

	private static void createTrees() throws NamingException {
		for (Tree tree : trees) {
			System.out.println("Root: " + tree.getRoot() + " Tree: " + tree.getTree());
			tree.build();
			System.out.println("Tree exist ? " + tree.exist());
		}
	}
	/**
	 * @throws NamingException
	 */
	private static void createAliases() throws NamingException {
		for (Alias alias : aliases) {
			alias.createAlias();
			System.out.println("Link: " + alias.getLink() + " -> To: " + alias.getTarget() + " created!");
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			createTrees();
			createAliases();
		} catch (NamingException e) {
			e.printStackTrace();
		} finally {
			try {
				if (initialContext != null) {
					initialContext.close();
				}
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		

	}

}
