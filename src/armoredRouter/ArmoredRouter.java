package armoredRouter;

import mindustry.gen.Building;
import mindustry.type.Category;
import mindustry.type.Item;
import mindustry.world.blocks.distribution.Router;

public class ArmoredRouter extends Router {
	public ArmoredRouter(String name) {
		super(name);
	}
	public class ArmoredRouterBuild extends RouterBuild {
		@Override
		public boolean acceptItem(Building source, Item item) {
			return super.acceptItem(source, item) && source.block.category == Category.distribution;
		}
	}
}
