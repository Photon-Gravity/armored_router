package armoredRouter;

import arc.struct.Seq;
import mindustry.content.Blocks;
import mindustry.content.Items;
import mindustry.content.TechTree;
import mindustry.ctype.UnlockableContent;
import mindustry.game.Objectives;
import mindustry.type.Category;
import mindustry.type.ItemStack;
import mindustry.world.Block;

import static mindustry.content.TechTree.TechNode;
import static mindustry.type.ItemStack.with;

public class ArmoredRouterBlocks {
	public static TechNode context = null;
	public static Block armoredRouter;
	public static void load(){
		armoredRouter = new ArmoredRouter("armored-router"){{
			size = 1;
			health = 160;
			requirements(Category.distribution, with(Items.metaglass, 3, Items.thorium, 3, Items.plastanium, 3));
		}};
		margeNode(Blocks.armoredConveyor, () -> node(armoredRouter));
	}
	private static void margeNode(UnlockableContent parent, Runnable children){
		context = TechTree.all.find(t -> t.content == parent);
		children.run();
	}
	private static void node(UnlockableContent content, ItemStack[] requirements, Seq<Objectives.Objective> objectives, Runnable children){
		TechNode node = new TechNode(context, content, requirements);
		if(objectives != null) node.objectives = objectives;

		TechNode prev = context;
		context = node;
		children.run();
		context = prev;
	}

	private static void node(UnlockableContent content, ItemStack[] requirements, Runnable children){
		node(content, requirements, null, children);
	}
	private static void node(UnlockableContent content, Runnable children){
		node(content, content.researchRequirements(), children);
	}

	private static void node(UnlockableContent block){
		node(block, () -> {});
	}
}
